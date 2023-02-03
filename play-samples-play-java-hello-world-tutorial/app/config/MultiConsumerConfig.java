package config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.User;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultiConsumerConfig implements Runnable {

    private KafkaConsumer<String, String> consumer;
    private String topic;
    private String message;

    private String group;
    private static final Logger logger = LoggerFactory.getLogger(MultiConsumerConfig.class);


    public MultiConsumerConfig(String topic,String message,String group) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, group);
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));
        this.topic = topic;
        this.message=message;
    }

    @Override
    public void run() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

            for (ConsumerRecord<String, String> record : records) {
                ObjectMapper mapper=new ObjectMapper();
                try {
                    User user=mapper.readValue(record.value(),User.class);
                    logger.warn(user.toString());
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                logger.warn("Received message: topic = {}, partition = {}, offset = {}, key = {}, value = {}",
                        record.topic(), record.partition(), record.offset(), record.key(), record.value()+message+"\n");
            }
            consumer.commitAsync();
        }

    }
}
