package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.EmailService;
import config.KafkaConsumerConfig;
import dao.UserDao;
import models.User;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import javax.inject.Inject;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Arrays;

public class KafkaListner {

    KafkaConsumerConfig consumerConfig;
    UserDao userDao;

    EmailService emailService;

    @Inject
    public KafkaListner(KafkaConsumerConfig consumerConfig, UserDao userDao, EmailService emailService) {
        this.consumerConfig = consumerConfig;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    public void getMessage(){
        Consumer<String, String> consumer= consumerConfig.consumerConfig();
        consumer.subscribe(Arrays.asList("test"));
        System.out.println("Success");
        int i = 0;
        while (true) {
            try{
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String,String> record: records) {
                    ObjectMapper objectMapper=new ObjectMapper();
                    User user=objectMapper.readValue(record.value(),User.class);
                    emailService.sendEmail();
                    System.out.println(userDao.addUser(user));
                    System.out.println(user);
                }
            } catch (JsonProcessingException | SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}