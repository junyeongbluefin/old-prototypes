package com.junyeongyu.prototype.kafka;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaListenerConfig {

    @KafkaListener(topics = "testTopic", groupId = "testGroup", containerFactory = "kafkaListenerContainerFactory") // TODO: need to be changed
    public void listen(String message) {
        System.out.println("Received Message in group foo: " + message);
    }
}
