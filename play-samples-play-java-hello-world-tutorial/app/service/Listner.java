package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Listner {
    private final Executor executor = Executors.newSingleThreadExecutor();
    KafkaConsumerConfig consumerConfig;
    UserDao userDao;

    EmailService emailService;

    @Inject
    public Listner( KafkaConsumerConfig consumerConfig, UserDao userDao, EmailService emailService) {
        this.consumerConfig = consumerConfig;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    public void start() {
        executor.execute(() -> {
            System.out.println("fgf");
            Consumer<String, String> consumer= consumerConfig.consumerConfig();
            consumer.subscribe(Arrays.asList("test"));
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("test");
                    CompletableFuture.runAsync(() -> processRecord(record), executor);
                }
            }
        });
    }

    private void processRecord(ConsumerRecord<String, String> record) {
        try{
            System.out.println("success");
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(record.value(), User.class);
            emailService.sendEmail();
            System.out.println(userDao.addUser(user));
            System.out.println(user);
        } catch (SQLException | JsonProcessingException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
