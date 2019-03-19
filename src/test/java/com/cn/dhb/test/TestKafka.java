package com.cn.dhb.test;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Test;

import java.util.Arrays;
import java.util.Properties;

/**
 * @Author: zhangjixu
 * @CreateDate: 2019/3/11
 * @Description:
 * @Version: 1.0.0
 */

public class TestKafka {

    @Test
    public void testKafka() {
        String topicName = "Hello-Kafka";
        Properties props = new Properties();
        props.put("bootstrap.servers", "");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList(topicName));
        System.out.println(" ======================= Subscribed to topic " + topicName);


        ConsumerRecords<String, String> records = consumer.poll(1);
        for (ConsumerRecord<String, String> record : records)
            System.out.printf(" ********************** offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());

    }


}
