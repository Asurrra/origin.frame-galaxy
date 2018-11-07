package com.cyw.origin.frame.galaxy.conf;
//
//import com.alibaba.dubbo.config.ApplicationConfig;
//import com.alibaba.dubbo.config.MonitorConfig;
//import com.alibaba.dubbo.config.ProtocolConfig;
//import com.alibaba.dubbo.config.RegistryConfig;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Created by weimob on 2017/7/25.
// */
////@Configuration
//public class DubboConf {
//    @Value("${dubbo.application.name}")
//    private String applicationName;
//    @Value("${dubbo.application.owner}")
//    private String applicationOwner;
//    @Value("${dubbo.registry.address}")
//    private String registryAddress;
//    @Value("${dubbo.protocol.name}")
//    private String protocolName;
//    @Value("${dubbo.protocol.port}")
//    private Integer protocolPort;
//    @Value("${dubbo.monitor.protocol}")
//    private String monitorProtocol;
//
//    /**
//     * dubbo应用配置.
//     *
//     * @return dubbo应用配置Bean
//     */
//    @Bean
//    public ApplicationConfig applicationConfig() {
//        ApplicationConfig ac = new ApplicationConfig();
//        ac.setName(applicationName);
//        ac.setOwner(applicationOwner);
//        return ac;
//    }
//
//    /**
//     * dubbo注册配置.
//     *
//     * @return dubboz注册配置Bean
//     */
//    @Bean
//    public RegistryConfig registryConfig() {
//        RegistryConfig rc = new RegistryConfig();
//        rc.setAddress(registryAddress);
//        return rc;
//    }
//
//    /**
//     * dubbo协议配置.
//     *
//     * @return dubbo协议配置Bean
//     */
//    @Bean
//    public ProtocolConfig protocolConfig() {
//        ProtocolConfig pc = new ProtocolConfig();
//        pc.setName(protocolName);
//        pc.setPort(protocolPort);
//        return pc;
//    }
//
//    /**
//     * dubbo监控配置.
//     *
//     * @return dubbo监控配置
//     */
//    @Bean
//    public MonitorConfig monitorConfig() {
//        MonitorConfig mc = new MonitorConfig();
//        mc.setProtocol(monitorProtocol);
//        return mc;
//    }
//}
