package Multimock.controller;
import Multimock.config.KafkaProducer;
import Multimock.model.DataCollection;
import Multimock.model.Item;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;


@RestController
@RequestMapping()
public class RestControllerExample {
    @PostMapping(path = "/collections", produces = "application/json")
    public String parseCollection(@RequestBody String bodyJson) {
        final String topic1 = "Game";
        final String topic2 = "Film";
        int i1=0, i2=0;
        String result;
        KafkaProducer kp = new KafkaProducer();
        Gson g = new Gson();
        DataCollection dc = g.fromJson(bodyJson, DataCollection.class);
        for (Item ic : dc.itemsCollect){
            //System.out.println(ic.name + " " + ic.type + " " + ic.genre);
            if (ic.type.equals(topic1)) {
                kp.sendOrder(topic1, g.toJson(ic));
                i1++;
            } else if (ic.type.equals(topic2)) {
                kp.sendOrder(topic2, g.toJson(ic));
                i2++;
            }
        }
        result = "Well done. "+ i1 +" messages sent to topic " + topic1 + " and "+ i2 + " messages sent to topic "+topic2;
        return result;
    }
}
