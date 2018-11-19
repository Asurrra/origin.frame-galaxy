package com.cyw.origin.frame.galaxy.aop;

import com.alibaba.fastjson.JSONObject;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.annotation.AnnotationUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

@Slf4j
public class AopLog implements InitializingBean {

    private static String LOG_TEMPLATE = "elapse:%s,interface:%s,method:%s,params:%s,result:%s";

    LinkedBlockingDeque<String> linkedBlockingDeque = new LinkedBlockingDeque<>();

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

        if(null != logIgnore){
            return ;
        }

        List params = new ArrayList();
        Object[] obj = joinPoint.getArgs();
        for(Object o:obj){
            if(o instanceof HttpServletRequest){
                continue;
            } else if (o instanceof HttpServletResponse){
                continue;
            } else{
                params.add(JSONObject.toJSON(o));
            }

        }
        String message = String.format(LOG_TEMPLATE,time,targetName,methodName, params, JSONObject.toJSON(object));
        linkedBlockingDeque.add(message);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Thread thread = new Thread(new ExecuteLogs(),"logThread");
        thread.start();
    }

    class ExecuteLogs implements Runnable{
        @Override
        public void run() {
            while (true) {
                try {
                    String message = linkedBlockingDeque.take();
                    log.info(message);
                } catch (InterruptedException e) {
                    log.error("日志信息从队列中取出发生异常", e);
                }
            }
        }
    }

}
