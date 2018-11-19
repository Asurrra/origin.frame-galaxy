package com.cyw.origin.frame.galaxy.bean;

import com.ctrip.framework.apollo.ConfigFile;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.core.enums.ConfigFileFormat;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

/**
 *
 * @author: yiwen.chang
 * @date: 2018/11/16
 * @version: 1.0.0
 */
public class TestBean {

    private String testBean() {
        ConfigFile configFile = ConfigService.getConfigFile("application-dev", ConfigFileFormat.YML);
        String content = configFile.getContent();
        return content;
    }

    public static void main(String[] args) {
        TestBean testBean = new TestBean();
        Yaml yaml = new Yaml();
        Map<String, Object> map = yaml.load(testBean.testBean());
        System.out.println(map);
    }

}
