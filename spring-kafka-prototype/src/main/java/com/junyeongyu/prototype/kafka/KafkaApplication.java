package com.junyeongyu.prototype.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// https://docs.spring.io/spring-kafka/docs/2.1.10.RELEASE/reference/html/_reference.html#kafka
// https://www.baeldung.com/spring-kafka
// https://kafka.apache.org/quickstart

/**
 * Before running application, Zookeeper and Kafka servers need to be running by following command
 * $ curl http://www-us.apache.org/dist/kafka/2.0.0/kafka_2.11-2.0.0.tgz --output kafka_2.11-2.0.0.tgz
 * $ tar -xzf kafka_2.11-2.0.0.tgz
 * $ cd kafka_2.11-2.0.0
 * $ bin/zookeeper-server-start.sh config/zookeeper.properties
 * $ bin/kafka-server-start.sh config/server.properties
 */
@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args); // .close()
    }
}
