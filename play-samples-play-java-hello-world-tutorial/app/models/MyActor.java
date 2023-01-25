package models;
//
//import akka.actor.AbstractActor;
//import akka.kafka.ConsumerSettings;
//import akka.kafka.Subscriptions;
//import akka.kafka.javadsl.Consumer;
//import akka.stream.ActorMaterializer;
//import akka.stream.javadsl.Sink;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class MyActor extends AbstractActor {
//
//    private final ActorMaterializer materializer;
//    private final String bootstrapServers;
//    private final String groupId;
//    private final Logger logger = LoggerFactory.getLogger(MyActor.class);
//
//    public MyActor(ActorMaterializer materializer, String bootstrapServers, String groupId) {
//        this.materializer = materializer;
//        this.bootstrapServers = bootstrapServers;
//        this.groupId = groupId;
//    }
//
//    public Receive createReceive() {
//        return receiveBuilder()
//                .match(String.class, s -> {
//                    ConsumerSettings<String, String> consumerSettings =
//                            ConsumerSettings.create(context().system(), new StringDeserializer(), new StringDeserializer())
//                                    .withProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092")
//                                    .withProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName())
//                                    .withProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName())
//                                    .withProperty(ConsumerConfig.GROUP_ID_CONFIG, "demoGroup");
//                    Consumer.plainSource(consumerSettings, Subscriptions.topics("test"))
//                            .runWith(Sink.actorRef(self(), "Completed"), materializer);
//                })
//                .match(ConsumerRecord.class, record -> {
//                    logger.info("Received message: {}", record.value());
//                })
//                .build();
//    }
//}
import akka.actor.AbstractActor;
import akka.kafka.ConsumerSettings;
import akka.kafka.Subscriptions;
import akka.kafka.javadsl.Consumer;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Sink;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import javax.inject.Inject;
import java.util.Date;

public class MyActor extends AbstractActor {
    private final ActorMaterializer materializer;
    private final String bootstrapServers;
    private final String groupId;
    private Scheduler scheduler;

    private EmailService emailJob;

    @Inject
    public MyActor(ActorMaterializer materializer, String bootstrapServers, String groupId,EmailService emailJob) {
        this.materializer = materializer;
        this.bootstrapServers = bootstrapServers;
        this.groupId = groupId;
        this.emailJob=emailJob;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    ConsumerSettings<String, String> consumerSettings =
                            ConsumerSettings.create(context().system(), new StringDeserializer(), new StringDeserializer())
                                    .withBootstrapServers(bootstrapServers)
                                    .withGroupId(groupId)
                                    .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

                    Consumer.plainSource(consumerSettings, Subscriptions.topics("test-topic"))
                            .runWith(Sink.actorRef(self(), ""), materializer);
                })
                .match(ConsumerRecord.class, record -> {
                    String data = (String) record.value();
                    // parse the data and extract email and message
                    emailJob.sendEmail();
                })
                .build();
    }
}
