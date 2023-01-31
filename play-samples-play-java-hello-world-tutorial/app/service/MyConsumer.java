package service;

import config.KafkaConsumerConfig;
import org.apache.kafka.clients.consumer.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class MyConsumer {
    private final Consumer<String,String> testConsumer;
    KafkaConsumerConfig consumerConfig;
    private final ConsumerRecordHandler handler;
    Handler testhandler;


    public MyConsumer(KafkaConsumerConfig consumerConfig,ConsumerRecordHandler handler) {
        this.testConsumer=consumerConfig.consumerConfig();
        this.handler = handler;
    }

    public void start() {
        new Thread(() -> {
//            Consumer<String, String> consumer= consumerConfig.consumerConfig();
            testConsumer.subscribe(Arrays.asList("test"));
            while (true) {
                ConsumerRecords<String, String> records = testConsumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    handler.handle(record);
                }
            }
        }).start();
    }

}
