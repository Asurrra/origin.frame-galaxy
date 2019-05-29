package com.cyw.origin.frame.galaxy.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.MessageListener;

import java.util.Optional;

/**
 *
 * @author: yiwen.chang
 * @date: 2018/12/3
 * @version: 1.0.0
 */
@Slf4j
public class ReceiverListener implements MessageListener<String, String> {

    @KafkaListener(topics = {"test"})
    public void listen(ConsumerRecord<String, String> record) {
        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            log.info("message : {}", kafkaMessage.get());
        }
    }

    @Override
    public void onMessage(ConsumerRecord<String, String> data) {

    }

}