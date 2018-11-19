package com.cyw.origin.frame.galaxy.conf;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("my-jdbc")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

//    @Bean
//    public CatMybatisPlugins catMybatisPlugins() {
//        return new CatMybatisPlugins();
//    }

}