package Multimock;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Main {

	/**
	public static void extractAndSend (String msg, String topic){
		Gson g = new Gson();
		Item ic = g.fromJson(msg, Item.class);
		System.out.println("Consumer. topic " + topic + ":" + ic.name + " ("+ic.genre+")");
		UserRepository ur = new UserRepository(new RedisTemplate<>()); //вот на этом этапе ошибка
		ur.create(ic);
	}
 	*/

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}





