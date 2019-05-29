package com.cyw.origin.frame.galaxy.bean;

import lombok.Data;

/**
 * 通过@ConfigurationProperties把配置注入到bean对象中
 * @author: yiwen.chang
 * @date: 2018/11/16
 * @version: 1.0.0
 */
@Data
//@ConfigurationProperties(prefix = "dubbo.application")
public class DubboBean {

    private String name;

    private String owner;

}