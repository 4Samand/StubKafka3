package Multimock.repository;

import Multimock.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public class ItemRepositoryImpl {

    final Logger logger = LoggerFactory.getLogger(ItemRepository.class);
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    //@PostConstruct

    public ItemRepositoryImpl(RedisTemplate redisTemplate) {
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

}

