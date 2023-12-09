package Multimock.controller;


import Multimock.model.Item;
import Multimock.repository.ItemRepositoryImpl;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;



@Component
//@Slf4j
public class KafkaListenerExample {
    @Autowired
    ItemRepositoryImpl ir = new ItemRepositoryImpl(new RedisTemplate());
    Gson g = new Gson();
    @KafkaListener(topics = "Game", groupId = "group1")
    void listener(String data) {
        System.out.println("Received message [{}] in topic Game:" + data);
        //log.info("Received message [{}] in group1", data);
        Item ic = g.fromJson(data, Item.class);
        ir.create(ic);
    }
    @KafkaListener(topics = "Film", groupId = "group1")
    void listener2(String data) {
        System.out.println("Received message [{}] in topic Film" + data);
        //log.info("Received message [{}] in group1", data);
    }
}
