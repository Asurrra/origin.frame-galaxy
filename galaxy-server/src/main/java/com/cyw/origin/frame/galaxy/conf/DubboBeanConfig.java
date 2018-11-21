package com.cyw.origin.frame.galaxy.conf;

import com.cyw.origin.frame.galaxy.bean.DubboBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * 通过@ConfigurationProperties把配置注入到bean对象中时，配置变化监控类
 * @author: yiwen.chang
 * @date: 2018/11/16
 * @version: 1.0.0
 */
@ConditionalOnProperty("dubbo.application")
@Component
@Slf4j
public class DubboBeanConfig {

    @Autowired
    private DubboBean dubboBean;

}