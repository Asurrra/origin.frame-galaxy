package com.cyw.origin.frame.galaxy;

import com.cyw.origin.frame.galaxy.util.EnvUtils;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * springboot启动文件
 *
 * @author yiwen.chang
 * @version 1.0.0
 */
@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class, scanBasePackages = "com.cyw.origin.frame.galaxy")
//@EnableApolloConfig({"weimob.arch-common", "my-jdbc", "redis", "common", "dubbo"})
@MapperScan("com.cyw.origin.frame.galaxy.*.dao")
public class GalaxyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(GalaxyApplication.class);
        application.setAdditionalProfiles(EnvUtils.getActiveProfiles());
        application.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.properties("spring.profiles.active:" + EnvUtils.getActiveProfiles());
        return builder.sources(GalaxyApplication.class);
    }

}