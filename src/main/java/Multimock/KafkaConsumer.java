package Multimock;

import Multimock.model.Item;

import Multimock.repository.UserRepository;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;


import javax.swing.*;


@EnableKafka
@SpringBootApplication
public class KafkaConsumer {


	public static void extractAndSend (String msg, String topic){
		Gson g = new Gson();
		Item ic = g.fromJson(msg, Item.class);
		System.out.println("Consumer. topic " + topic + ":" + ic.name + " ("+ic.genre+")");
		UserRepository ur = new UserRepository(new RedisTemplate<>()); //вот на этом этапе ошибка
		ur.create(ic);


	}

	@KafkaListener(topics="Game")
	public void gameListener(String msg) throws Exception{
		extractAndSend(msg, "Game");
	}

	@KafkaListener(topics="Film")
	public void filmListener(String msg) throws Exception{
		extractAndSend(msg, "Film");
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumer.class, args);

	}

}





