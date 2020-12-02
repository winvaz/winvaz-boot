package com.icore.winvaz.javase.basic.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Deciption Kafka生产者测试
 * @Author wdq
 * @Create 2019/12/17 17:36
 */
public class ProducerDemo extends TimerTask {
    public static void main(String[] args) {
        // 定时任务发送数据
        ProducerDemo producerDemo = new ProducerDemo();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(producerDemo, 0, 1000*30);
        System.out.println("The TimerTaskDemo Started:>>>>>>>>>");
    }

    @Override
    public void run() {
        sendMessage();
    }

    public void sendMessage() {
        // 设置参数
        Properties properties = new Properties();
        // 设置主题
        String topic = "test";

        // 设置Kafka参数
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);

        // 键值序列化(网络传输)
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = null;
        try {
            producer = new KafkaProducer<String, String>(properties);
            // 格式化日期
            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
            ProducerRecord record = new ProducerRecord(topic, topic + "-K-" + format, topic + "-V-" + format);
            System.out.println("Thie is Message:>>>>>" + record.toString());
            // 发布
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}