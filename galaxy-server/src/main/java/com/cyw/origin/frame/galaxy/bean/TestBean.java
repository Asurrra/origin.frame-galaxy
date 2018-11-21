package com.cyw.origin.frame.galaxy.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author: yiwen.chang
 * @date: 2018/11/16
 * @version: 1.0.0
 */
@Data
public class TestBean {

    @Value("${spring.common:100}")
    private String springCommon;

    @Value("${batch:200}")
    private String batch;

    /** ---------------------- yml bean ---------------------- */
//    private String testBean() {
//        ConfigFile configFile = ConfigService.getConfigFile("application-dev", ConfigFileFormat.YML);
//        String content = configFile.getContent();
//        return content;
//    }
//
//    public static void main(String[] args) {
//        TestBean testBean = new TestBean();
//        Yaml yaml = new Yaml();
//        Map<String, Object> map = yaml.load(testBean.testBean());
//        System.out.println(map);
//    }

}