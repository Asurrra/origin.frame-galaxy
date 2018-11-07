package com.cyw.origin.frame.galaxy.aop;/*
package com.cyw.origin.frame.galaxy.aop;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.weimob.saas.promotion.common.model.BaseRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class ControllerAspect {

    @Value("${dev.remote.ip:}")
    private String devRemoteIp;

    @Pointcut("within(@org.springframework.stereotype.Controller *) || within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerAspect() {
    }

    @Before("controllerAspect()")
    public void before(JoinPoint joinPoint) {
        try {
            for (Object o : joinPoint.getArgs()) {
                if (o instanceof BaseRequest
                        && RequestContextHolder.getRequestAttributes() instanceof ServletRequestAttributes) {
                    ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder
                            .getRequestAttributes();
                    HttpServletRequest request = sra.getRequest();
                    String clientIps = request.getHeader("x-forwarded-for");
                    if (StringUtils.isNotEmpty(devRemoteIp)) {
                        clientIps = devRemoteIp;
                    }
                    BaseRequest baseRequest = (BaseRequest) o;
                    if (StringUtils.isNotEmpty(clientIps)) {
                        String clientIp = clientIps.split(",")[0];
                        baseRequest.setIp(clientIp.trim());
                    }
                    if (baseRequest.getIsH5() == null) {
                        baseRequest.setIsH5(false);
                    }
                    break;
                }
            }
        } catch (Exception e) {
            log.error(joinPoint.toLongString(), e);
        }
    }

}
*/
