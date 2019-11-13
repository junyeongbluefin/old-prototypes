package com.junyeongyu.prototype.kafka;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HelloController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/")
    public String send() {
        final String testMessage = "Test";
        sendMessage(testMessage);

        return "Send success";
    }

    public void sendMessage(String msg) {
        final String topicName = "testTopic"; // TODO: need to be changed
        kafkaTemplate.send(topicName, msg);
    }

}
