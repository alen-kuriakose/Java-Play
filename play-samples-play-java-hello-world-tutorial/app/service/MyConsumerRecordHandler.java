package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.EmailService;
import config.KafkaConsumerConfig;
import config.SchedulerConfig;
import dao.UserDao;
import models.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.quartz.SchedulerException;

import javax.inject.Inject;
import java.sql.SQLException;

public class MyConsumerRecordHandler implements ConsumerRecordHandler {

    KafkaConsumerConfig consumerConfig;
    UserDao userDao;

    EmailService emailService;

    SchedulerConfig schedulerConfig;

    @Inject
    public MyConsumerRecordHandler(KafkaConsumerConfig consumerConfig, UserDao userDao, EmailService emailService,SchedulerConfig schedulerConfig) {
        this.consumerConfig = consumerConfig;
        this.userDao = userDao;
        this.emailService = emailService;
        this.schedulerConfig=schedulerConfig;
    }

    @Override
    public void handle(ConsumerRecord<String, String> record) {
        try {
            System.out.println("Success 2");
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println("Test 2");
            User user = objectMapper.readValue(record.value(), User.class);
//            emailService.sendEmail();
//            schedulerConfig.runSchedule();
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
