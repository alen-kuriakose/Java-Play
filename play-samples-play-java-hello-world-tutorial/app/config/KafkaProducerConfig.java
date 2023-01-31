package config;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.util.Properties;

public class KafkaProducerConfig {
    public Producer producerConfig() throws IOException {
            Properties props= new Properties();
//            Map<String, Object> configurations = new HashMap<>();
//            props.load(getClass().getClassLoader().getResourceAsStream("kafka-producer.properties"));
//            props.put("bootstrap.servers", "localhost:29092");
//            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            Producer<Object, String> producer = new KafkaProducer<Object, String>(props);
            return producer;
    }
}
