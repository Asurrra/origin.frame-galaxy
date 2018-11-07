package com.cyw.origin.frame.galaxy.conf;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.beans.factory.annotation.Autowire;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import java.sql.SQLException;
//
///**
// * Created by weimob on 2017/7/24.
// */
////@Configuration
////@EnableTransactionManagement(proxyTargetClass = true)
//public class MysqlConf {
//    @Value("${datasource.jdbc.url}")
//    private String url;
//    @Value("${datasource.jdbc.username}")
//    private String username;
//    @Value("${datasource.jdbc.password}")
//    private String password;
//    @Value("${datasource.pool.initialSize}")
//    private int initialSize;
//    @Value("${datasource.pool.minIdle}")
//    private int minIdle;
//    @Value("${datasource.pool.maxActive}")
//    private int maxActive;
//    @Value("${datasource.pool.maxWait}")
//    private long maxWait;
//    @Value("${datasource.validationQuery}")
//    private String validationQuery;
//    @Value("${datasource.testOnBorrow}")
//    private boolean testOnBorrow;
//    @Value("${datasource.testOnReturn}")
//    private boolean testOnReturn;
//    @Value("${datasource.testWhileIdle}")
//    private boolean testWhileIdle;
//    @Value("${datasource.timeBetweenEvictionRunsMillis}")
//    private long timeBetweenEvictionRunsMillis;
//    @Value("${datasource.minEvictableIdleTimeMillis}")
//    private long minEvictableIdleTimeMillis;
//    @Value("${datasource.removeAbandoned}")
//    private boolean removeAbandoned;
//    @Value("${datasource.removeAbandonedTimeout}")
//    private int removeAbandonedTimeout;
//    @Value("${datasource.logAbandoned}")
//    private boolean logAbandoned;
//    @Value("${datasource.filters}")
//    private String filters;
//    private DruidDataSource dds;
//
//    /**
//     * 数据库连接池配置.
//     * @return dataSource数据库连接池Bean
//     * @throws SQLException 异常
//     */
//    @Bean(value = "dataSource", initMethod = "init", destroyMethod = "close", autowire = Autowire.BY_TYPE)
//    public DruidDataSource druidDataSource() throws SQLException {
//        dds = new DruidDataSource();
//        dds.setUrl(url);
//        dds.setUsername(username);
//        dds.setPassword(password);
//        dds.setInitialSize(initialSize);
//        dds.setMinIdle(minIdle);
//        dds.setMaxActive(maxActive);
//        dds.setMaxWait(maxWait);
//        dds.setValidationQuery(validationQuery);
//        dds.setTestOnBorrow(testOnBorrow);
//        dds.setTestOnReturn(testOnReturn);
//        dds.setTestWhileIdle(testWhileIdle);
//        dds.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
//        dds.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//        dds.setRemoveAbandoned(removeAbandoned);
//        dds.setRemoveAbandonedTimeout(removeAbandonedTimeout);
//        dds.setLogAbandoned(logAbandoned);
//        dds.setFilters(filters);
//        return dds;
//    }
//}
