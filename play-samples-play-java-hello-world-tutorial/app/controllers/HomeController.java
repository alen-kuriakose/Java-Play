package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import play.data.Form;
import play.mvc.*;
import play.data.FormFactory;
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

//    MyActor myActor;


    @Inject
    public HomeController(AnnouncementDao announcementDao, FormFactory dataForm, EmailJob emailJob, KafkaProducerFn kafkaProducerFn,KafkaListner kafkaListner) {
        this.announcementDao = announcementDao;
        this.dataForm = dataForm;
        this.emailJob = emailJob;
        this.kafkaProducerFn = kafkaProducerFn;
        this.kafkaListner = kafkaListner;
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
//        myActor.createReceive();
//        kafkaListner.getMessage();
        return ok("Success");
    }

    public Result getmessage() throws JsonProcessingException {
        kafkaListner.getMessage();
//        kafkaListner.pollAndProcessMessages();
        return ok("Message received from cluster");
    }

    public Result tutorial() {
        return ok();
    }

}
