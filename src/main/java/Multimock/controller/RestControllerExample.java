package Multimock.controller;
import Multimock.Sender.KafkaSender;
import Multimock.model.DataCollection;
import Multimock.model.Item;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.Gson;


@RestController
@RequestMapping()
public class RestControllerExample {


    @Autowired
    KafkaSender kp = new KafkaSender();


    @PostMapping(path = "/collections", produces = "application/json")
    public String parseCollection(@RequestBody String bodyJson) {
        final String TOPIC_1 = "Game";
        final String TOPIC_2 = "Film";
        String result;
        int i1=0, i2=0;

        Gson g = new Gson();
        DataCollection dc = g.fromJson(bodyJson, DataCollection.class);
        for (Item ic : dc.itemsCollect){
            if (ic.type.equals(TOPIC_1)) {
                kp.sendMessage(TOPIC_1, g.toJson(ic));
                i1++;
            } else if (ic.type.equals(TOPIC_2)) {
                kp.sendMessage(TOPIC_2, g.toJson(ic));
                i2++;
            }
        }
        result = "Well done. "+ i1 +" messages sent to topic " + TOPIC_1 + " and "+ i2 + " messages sent to topic "+TOPIC_2;
        return result;
    }
}
