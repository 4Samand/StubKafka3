package Multimock.repository;

import Multimock.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;

import java.util.Map;


@Repository
public class UserRepository {

    final Logger logger = LoggerFactory.getLogger(UserRepository.class);
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    //@PostConstruct

    public UserRepository(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void create(Item item) {
        hashOperations.put("ITEM", item.getGenre(), item);
        logger.info(String.format("Item with name %s saved", item.getName()));
    }

    public Item get(String userId) {
        return (Item) hashOperations.get("ITEM", userId);
    }

    public Map<String, Item> getAll(){
        return hashOperations.entries("ITEM");
    }
    /**
    public void update(Item item) {
        hashOperations.put("USER", Item.getUserId(), user);
        logger.info(String.format("User with ID %s updated", user.getUserId()));
    }

    public void delete(String userId) {
        hashOperations.delete("USER", userId);
        logger.info(String.format("User with ID %s deleted", userId));
    } */
}

