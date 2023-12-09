package Multimock.Sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
//@Slf4j
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topicName, String message) {
        System.out.println("Producer. topic <"+topicName+">"+": "+message);
        System.out.println("--------------------------------");
        //log.info("Producer. topic <"+topicName+">"+": "+message);
        //log.info("--------------------------------");

        kafkaTemplate.send(topicName, message);
    }
}
