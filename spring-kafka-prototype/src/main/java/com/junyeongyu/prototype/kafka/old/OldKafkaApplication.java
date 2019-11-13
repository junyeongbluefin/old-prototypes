package com.junyeongyu.prototype.kafka.old;

import org.springframework.boot.SpringApplication;

// https://docs.spring.io/spring-kafka/docs/2.1.10.RELEASE/reference/html/_reference.html#kafka
// https://www.baeldung.com/spring-kafka
// @SpringBootApplication
public class OldKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OldKafkaApplication.class, args); // .close()
    }
}
