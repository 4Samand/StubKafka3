package Multimock.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@RedisHash("Item")
public class Item {
    //@Id
    public String name;
    public String type;
    public String genre;

    public Item (String name, String type, String genre) {
        this.name = name;
        this.type = type;
        this.genre = genre;
    }
    public String getGenre() {
        return genre;
    }
    public void setId(String genre) {
        this.genre = genre;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


}
