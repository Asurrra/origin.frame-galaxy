package com.cyw.origin.frame.galaxy.auth;

import com.alibaba.dubbo.common.json.JSON;
import com.cyw.origin.frame.galaxy.auth.annotation.AuthedUser;
import com.cyw.origin.frame.galaxy.common.response.SessionInfo;
import com.cyw.origin.frame.galaxy.util.CryptUtils;
import com.cyw.origin.frame.galaxy.util.lang.StringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Data
@Slf4j
@ControllerAdvice
public class AuthedArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthedUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String token = request.getHeader("token");
        SessionInfo sessionInfo = new SessionInfo();
        if (StringUtil.isNotEmpty(token)) {
            log.info("++ token = {} ++",token);
            if (StringUtil.isEmpty(token)) {
                throw new Exception("用户登陆信息失效");
            }
            sessionInfo = this.getUserFromRedis(token);

        }
        return sessionInfo;
    }

    private SessionInfo getUserFromRedis(String key) {
        try {
            /**String token = "svzan71gigAOdgHqZL0lijHI+M/AMLt1rdEnmHKZanAZ1Jc4rSHwUf5JB0PqdHRp22PGYYWQEma/DaXG8rvMi5VqY6OSjKWczmPS1EIhBS6gh2B1JZLOAw=="; */
            String jsonInfo = CryptUtils.decryptDES(key, CryptUtils.key);
            log.info("++ jsonInfo = {} ++", jsonInfo);
            SessionInfo sessionInfo = JSON.parse(jsonInfo, SessionInfo.class);
            return sessionInfo;
        } catch (Exception e) {
            log.error("++get user info failed! + {}", e);
            return null;
        }
    }


}
