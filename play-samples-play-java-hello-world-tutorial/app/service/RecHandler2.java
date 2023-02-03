package service;

import config.MultiConsumerConfig;

public class RecHandler2 {
    public void testing() {
        String topic = "test";
        String group1="demoGroup";
        String group2="demoGroup2";
        MultiConsumerConfig consumer1 = new MultiConsumerConfig(topic,"Consumer 1",group1);
        MultiConsumerConfig consumer2 = new MultiConsumerConfig(topic,"Consumer 2",group2);
        new Thread(consumer1).start();
        new Thread(consumer2).start();
    }

}
