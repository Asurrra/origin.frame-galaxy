//package com.cyw.origin.frame.galaxy.conf;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
///**
// * Created by weimob on 2017/7/25.
// */
//@Configuration
//@MapperScan(basePackages = {"com.weimob.saas.promotion.bargain.dao"}, sqlSessionFactoryRef = "sqlSessionFactory")
//@AutoConfigureAfter(MysqlConf.class)
//public class MybatisConf {
//    @Autowired
//    private DruidDataSource dataSource;
//    @Value("${datasource.mapper.path}")
//    private String mapperPath;
//
//    /**
//     * mybatis session工厂配置.
//     *
//     * @return sqlSessionFactory session工厂Bean
//     * @throws Exception 异常
//     */
//    @Bean("sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
//        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
//        ssfb.setDataSource(dataSource);
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        ssfb.setMapperLocations(resolver.getResources(mapperPath));
//        return ssfb.getObject();
//    }
//
//    /**
//     * 事务管理器配置.
//     *
//     * @return transactionManager事务管理器Bean
//     */
//    @Bean("transactionManager")
//    public DataSourceTransactionManager dataSourceTransactionManager() {
//        DataSourceTransactionManager dstm = new DataSourceTransactionManager();
//        dstm.setDataSource(dataSource);
//        return dstm;
//    }
//}
