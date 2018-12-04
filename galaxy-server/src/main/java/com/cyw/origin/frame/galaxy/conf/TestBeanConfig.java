package com.cyw.origin.frame.galaxy.conf;

import com.cyw.origin.frame.galaxy.bean.DubboBean;
import com.cyw.origin.frame.galaxy.bean.TestBean;
import com.cyw.origin.frame.galaxy.listener.CommonBeanListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestBeanConfig {

    @Bean
    public TestBean testBean() {
        return new TestBean();
    }

    @Bean
    public DubboBean dubboBean() {
        return new DubboBean();
    }

    @Bean
    public CommonBeanListener commonBeanListener() {
        return new CommonBeanListener();
    }

//    @Bean
//    public CatMybatisPlugins catMybatisPlugins() {
//        return new CatMybatisPlugins();
//    }

}