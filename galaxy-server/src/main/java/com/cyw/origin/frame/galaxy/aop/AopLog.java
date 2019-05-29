package com.cyw.origin.frame.galaxy.aop;

import com.alibaba.fastjson.JSONObject;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.*;

@Component
@Slf4j
@Aspect
public class AopLog implements InitializingBean {

    private static String LOGTEMPLATE = "elapse:%s,interface:%s,method:%s,params:%s,result:%s";

    Deque<String> deque = new ArrayDeque<>();

    @Around("execution(* com.cyw.origin.frame.galaxy.*.service..*(..))" )
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Transaction cat = Cat.newTransaction(getInterface(joinPoint),getRequestMethod(joinPoint));
        Object obj ;
        Long elapsedMills = System.currentTimeMillis();

        try {
            obj = joinPoint.proceed();
            cat.setStatus(Transaction.SUCCESS);
        } catch (Throwable throwable) {
            cat.setStatus(throwable);
            throwable.printStackTrace();
            throw throwable;
        }finally {
            cat.complete();
        }

        elapsedMills = System.currentTimeMillis() - elapsedMills;
        aopLogs(elapsedMills, joinPoint, obj);
        return obj;
    }


    private String getRequestMethod(ProceedingJoinPoint joinPoint){
        try{
            Signature signature =  joinPoint.getSignature();

            if(!(signature instanceof MethodSignature)){
                throw new IllegalArgumentException("obtain method  failure!");
            }
            MethodSignature methodSignature = (MethodSignature) signature;
            return methodSignature.getName();
        }catch(Exception e){
            e.printStackTrace();
        }

        return "";
    }

    private String getInterface(ProceedingJoinPoint joinPoint){
        return joinPoint.getTarget().getClass().getName();
    }

    private Method getMethod(ProceedingJoinPoint joinPoint){
        Signature signature =  joinPoint.getSignature();

        if(!(signature instanceof MethodSignature)){
            throw new IllegalArgumentException("obtain method  failure!");
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        return methodSignature.getMethod();
    }

    private void aopLogs(Long time,ProceedingJoinPoint joinPoint, Object object){
        String targetName = getInterface(joinPoint);
        String methodName = getRequestMethod(joinPoint);

        LogIgnore logIgnore = AnnotationUtils.findAnnotation(getMethod(joinPoint),LogIgnore.class);
        RequireOperationLog requireOperationLog = AnnotationUtils.findAnnotation(getMethod(joinPoint), RequireOperationLog.class);

        if(null != logIgnore){
            return ;
        }

        List params = new ArrayList();
        Object[] obj = joinPoint.getArgs();
        for(Object o:obj){
            if(o instanceof HttpServletRequest){
                Map<String,String[]> map = ((HttpServletRequest) o).getParameterMap();
                for(Map.Entry<String,String[]> entry: map.entrySet()){
                    params.add(entry.getKey()+ "=" + Arrays.toString(entry.getValue()));
                }

                if (requireOperationLog != null) {
                    HttpServletRequest request = (HttpServletRequest) o;
                    String username = request.getAttribute("username") != null ? (String) request.getAttribute("username") : "";
                    String account = request.getAttribute("account") != null ? (String) request.getAttribute("account") : "";
                    String operationType = request.getAttribute("operationType") != null ? (String) request.getAttribute("operationType") : "";
                    String menuName = request.getAttribute("menuName") != null ? (String) request.getAttribute("menuName") : "";
                    String description = String.format("请求参数：%s， 返回参数：%s", params, JSONObject.toJSONString(object));
                    String systemName = request.getAttribute("systemName") != null ? (String) request.getAttribute("systemName") : "";
                }
            } else if (o instanceof HttpServletResponse){
                continue;
            } else{
                params.add(JSONObject.toJSONString(o));
            }

        }
//        String message = String.format(LOGTEMPLATE,time,targetName,methodName, params, JSONObject.toJSONString(object));
//        deque.add(message);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Thread thread = new Thread(new ExecuteLogs(),"logThread");
        thread.start();
    }

    class ExecuteLogs implements Runnable{

        @Override
        public void run() {
            while (true){
                if(deque.size()> 0){
                    String message = deque.pollFirst();
                    log.info(message);
                }else {
                    try {
                        Thread.sleep(5L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
