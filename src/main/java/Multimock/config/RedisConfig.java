package Multimock.config;

import Multimock.model.Item;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;



@Configuration
public class RedisConfig {


      @Bean
      public LettuceConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory lcf = new LettuceConnectionFactory();
        lcf.setHostName("reddis://password@193.104.57.59:6379");
        //lcf.setPort(6379);
        //lcf.setPassword("password");
        lcf.afterPropertiesSet();
        return lcf;
    }


    @Bean
    public RedisTemplate<String, Item> redisTemplate() {
        RedisTemplate<String, Item> redisTemplate = new RedisTemplate<String ,Item>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }




}



