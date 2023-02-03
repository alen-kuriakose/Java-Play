package service;

import config.KafkaConsumerConfig;
import config.MultiConsumerConfig;
import org.apache.kafka.clients.consumer.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class MyConsumer {
    private final Consumer<String,String> testConsumer;
    KafkaConsumerConfig consumerConfig;
    private final ConsumerRecordHandler handler;


    public MyConsumer(KafkaConsumerConfig consumerConfig,ConsumerRecordHandler handler) {
        this.testConsumer=consumerConfig.consumerConfig();
        this.handler = handler;
    }

    public void start() {
        new Thread(() -> {
//            Consumer<String, String> consumer= consumerConfig.consumerConfig();
//            testConsumer.subscribe(Arrays.asList("test"));
//            while (true) {
//                ConsumerRecords<String, String> records = testConsumer.poll(Duration.ofMillis(100));
//                for (ConsumerRecord<String, String> record : records) {
//                    handler.handle(record);
//                }
//            }
            String topic="test";
//            MultiConsumerConfig consumer1 = new MultiConsumerConfig(topic);
        }).start();
    }
    public void test(){
        new Thread(() -> {
//            Consumer<String, String> consumer= consumerConfig.consumerConfig();
//            testConsumer.subscribe(Arrays.asList("test"));
//            while (true) {
//                ConsumerRecords<String, String> records = testConsumer.poll(Duration.ofMillis(100));
//                for (ConsumerRecord<String, String> record : records) {
//                    handler.handle(record);
//                }
//            }
            String topic="test";
//            MultiConsumerConfig consumer2 = new MultiConsumerConfig(topic);
        }).start();
    }



}
