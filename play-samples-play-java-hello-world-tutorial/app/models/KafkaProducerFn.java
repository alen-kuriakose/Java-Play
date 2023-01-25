package models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.inject.Inject;
import java.io.IOException;

public class KafkaProducerFn {

    @Inject
    public KafkaProducerFn(KafkaProducerConfig producer) {
        this.producer = producer;
    }

    KafkaProducerConfig producer;

    public void send(String data) {
        try{
            Producer kafkaProducer= producer.producerConfig();
            System.out.println("Success data sent to cluster.");
            kafkaProducer.send(new ProducerRecord<>("test", data));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
