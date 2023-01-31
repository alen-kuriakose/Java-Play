package service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface ConsumerRecordHandler {
    void handle(ConsumerRecord<String, String> record);
}
