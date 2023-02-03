package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.KafkaConsumerConfig;
import dao.AnnouncementDao;
import models.*;
import org.apache.kafka.clients.consumer.Consumer;
import play.data.Form;
import play.mvc.*;
import play.data.FormFactory;
import service.*;

import javax.inject.Inject;

import java.io.IOException;
import java.sql.SQLException;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    AnnouncementDao announcementDao;
    FormFactory dataForm;
    EmailJob emailJob;

    KafkaProducerFn kafkaProducerFn;

    KafkaListner kafkaListner;

    MyConsumerRecordHandler recordHandler;

//    @Inject
//    Consumer<String, String> consumer;
    KafkaConsumerConfig consumer;
    Listner listner;
//    MyActor myActor;

    RecHandler2 handler2;


    @Inject
    public HomeController(AnnouncementDao announcementDao, FormFactory dataForm, EmailJob emailJob, KafkaProducerFn kafkaProducerFn,KafkaListner kafkaListner,MyConsumerRecordHandler recordHandler,Listner listner,KafkaConsumerConfig consumer,RecHandler2 handler2) {
        this.announcementDao = announcementDao;
        this.dataForm = dataForm;
        this.emailJob = emailJob;
        this.kafkaProducerFn = kafkaProducerFn;
        this.kafkaListner = kafkaListner;
        this.recordHandler=recordHandler;
        this.listner=listner;
        this.consumer=consumer;
        this.handler2=handler2;
//        this.myActor=myActor;
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index(Http.Request request) throws SQLException, ClassNotFoundException, IOException {
        Form<User> form=dataForm.form(User.class).bindFromRequest(request);
        User user=form.get();
        ObjectMapper mapper=new ObjectMapper();
        String data=mapper.writeValueAsString(user);
        kafkaProducerFn.send(data);
        return ok("Success");
    }

    public Result getmessage() throws JsonProcessingException {
//        kafkaListner.getMessage();
//        kafkaListner.pollAndProcessMessages();
//        Consumer<String,String> con=consumer.consumerConfig();
        MyConsumer myConsumer = new MyConsumer(consumer, recordHandler);
        Myconsumer2 myconsumer2=new Myconsumer2(consumer,recordHandler);
//        myConsumer.start();
//        myConsumer.test();
//        myconsumer2.start();
//        listner.start();
        handler2.testing();
        return ok("Message received test");
    }

    public Result tutorial() {
        return ok();
    }

}
