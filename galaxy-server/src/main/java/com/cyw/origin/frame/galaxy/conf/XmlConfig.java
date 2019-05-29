package com.cyw.origin.frame.galaxy.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = {"classpath:applicationContext-dubbo.xml", "applicationContext-thread-pool.xml"})
public class XmlConfig {

}