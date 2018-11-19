package com.cyw.origin.frame.galaxy.listener;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * apollo-common配置文件监听类
 * @author: yiwen.chang
 * @date: 2018/11/13
 * @version: 1.0.0
 */
@Slf4j
public class CommonBeanListener {

    @ApolloConfig("common")
    private Config config;

    public String getCommonValue(String key, String defaultValue) {
        return config.getProperty(key, defaultValue);
    }

    @ApolloConfigChangeListener("common")
    private void anotherOnChange(ConfigChangeEvent changeEvent) {
        Set<String> changes = changeEvent.changedKeys();
        for (String change : changes) {
            ConfigChange configChange = changeEvent.getChange(change);
            log.info(String.format("Found change - key: %s, oldValue: %s," + " newValue: %s, changeType: %s",
                    configChange.getPropertyName(), configChange.getOldValue(), configChange.getNewValue(), configChange.getChangeType()));
        }
    }

}