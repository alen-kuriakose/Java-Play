package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.EmailService;
import config.KafkaConsumerConfig;
import dao.UserDao;
import models.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import javax.inject.Inject;
import java.sql.SQLException;

public class Handler implements ConsumerRecordHandler{
    KafkaConsumerConfig consumerConfig;
    UserDao userDao;

    EmailService emailService;

    @Inject
    public Handler(KafkaConsumerConfig consumerConfig, UserDao userDao, EmailService emailService) {
        this.consumerConfig = consumerConfig;
        this.userDao = userDao;
        this.emailService = emailService;
    }
    @Override
    public void handle(ConsumerRecord<String, String> record) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println("Test Handler");
            User user = objectMapper.readValue(record.value(), User.class);
            System.out.println(userDao.addUser(user));
            System.out.println(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
