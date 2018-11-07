package com.cyw.origin.frame.galaxy.conf;
//
//import com.weimob.soa.configcenter.WccPropertyPlaceholderConfigurer;
//import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//public class WccConf {
//
//    @Bean
//    public PropertyPlaceholderConfigurer getBargainProperties() {
//        return getConfigurer("saas.promotion-bargain", Arrays.asList("bargain-service.properties"));
//    }
//
//    @Bean
//    public PropertyPlaceholderConfigurer getCommonProperties() {
//        return getConfigurer("weimob.arch-common", Arrays.asList("dubbo-common.properties"));
//    }
//
//    private static PropertyPlaceholderConfigurer getConfigurer(String applicationName, List<String> locations) {
//        WccPropertyPlaceholderConfigurer configurer = new WccPropertyPlaceholderConfigurer();
//        configurer.setApplicationName(applicationName);
//        configurer.setIgnoreUnresolvablePlaceholders(true);
//        configurer.setLocations(locations);
//        return configurer;
//    }
//
//}
