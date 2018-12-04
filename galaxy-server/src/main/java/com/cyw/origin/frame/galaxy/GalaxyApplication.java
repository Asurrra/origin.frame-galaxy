package com.cyw.origin.frame.galaxy;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot启动文件
 *
 * @author yiwen.chang
 * @version 1.0.0
 */
@SpringBootApplication(scanBasePackages = "com.cyw.origin.frame.galaxy")
@EnableApolloConfig({"weimob.arch-common", "my-jdbc", "redis", "common", "dubbo", "application-dev"})
@MapperScan("com.cyw.origin.frame.galaxy.**.dao")
public class GalaxyApplication {
    public static void main(String[] args) {
        SpringApplication.run(GalaxyApplication.class, args);
    }


/*
    @Bean
    public FilterRegistrationBean catFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(catFilter());
        registration.setName("catFilter");
        registration.addUrlPatterns("");
        registration.setOrder(5);
        return registration;
    }

    @Bean(name = "catFilter")
    public Filter catFilter() {
        return new CatFilter();
    }

    @Bean
    public FilterRegistrationBean traceFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(traceFilter());
        registration.setName("traceFilter");
        registration.addUrlPatterns("");
        registration.addInitParameter("isCatLog","true");
        registration.setOrder(10);
        return registration;
    }

    @Bean(name = "traceFilter")
    public Filter traceFilter() {
        return new TraceInitFilter();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new AuthedArgumentResolver());
    }
*/

//    @Bean
//    MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setLocation("D:/data/upload/temp");
//        return factory.createMultipartConfig();
//    }
}