package com.icore.winvaz.javase.basic.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * @Deciption Kafka消费者测试
 * @Author wdq
 * @Create 2019/12/17 17:52
 */
public class ConsumerDemo {
    public static void main(String[] args) {
        // 设置参数
        Properties properties = new Properties();
        // 设置主题
        String topic = "test";

        // 设置Kafka参数
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "test");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "earliest");
        properties.put("session.timeout.ms", "30000");

        // 键值反序列化(网络传输)
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList(topic));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            records.forEach(record -> {
                System.out.printf("offset = %d, value = %s", record.offset(), record.value());
                System.out.println("======================>");
            });
        }
    }
}
