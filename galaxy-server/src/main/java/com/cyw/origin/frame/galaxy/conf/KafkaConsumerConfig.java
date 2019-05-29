package com.cyw.origin.frame.galaxy.conf;

import com.cyw.origin.frame.galaxy.kafka.ReceiverListener;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * @author: yiwen.chang
 * @date: 2018/12/6
 * @version: 1.0.0
 */
@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${bootstrap.servers}")
    private String servers;
    @Value("${enable.auto.commit}")
    private boolean enableAutoCommit;
    @Value("${session.timeout.ms}")
    private String sessionTimeout;
    @Value("${auto.commit.interval.ms}")
    private String autoCommitInterval;
    @Value("${group.id}")
    private String groupId;
    @Value("${auto.offset.reset}")
    private String autoOffsetReset;
    @Value("${concurrency}")
    private int concurrency;

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(concurrency);
        factory.getContainerProperties().setPollTimeout(1500);
        return factory;
    }

    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    public Map<String, Object> consumerConfigs() {
        Map<String, Object> propsMap = new HashMap<>(10);
        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
        propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);
        propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeout);
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        propsMap.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 3000);
        return propsMap;
    }

    @Bean
    public ReceiverListener receiverListener() {
        return new ReceiverListener();
    }

    /** 消费者容器配置信息 */
    @Bean
    public ContainerProperties containerProperties() {
        String compile = ".*[tT]opic.*";
        /**匹配满足正则的topic*/
        Pattern topicPattern = Pattern.compile(compile);
        /**订阅满足正则表达式的topic*/
        ContainerProperties containerProperties = new ContainerProperties(topicPattern);
        /**订阅的topic的消息用myMessageListener去处理*/
        containerProperties.setMessageListener(receiverListener());
        return containerProperties;
    }

    @Bean
    public KafkaMessageListenerContainer<String, String> kafkaMessageListenerContainer() {
        return new KafkaMessageListenerContainer<>(consumerFactory(), containerProperties());
    }

}