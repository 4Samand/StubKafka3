package Multimock.config;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


public class KafkaProducer {
    private String kafkaServer="localhost:9092";
    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                kafkaServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        return props;
    }
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Autowired
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        final KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory());
        return kafkaTemplate;
    }
    //@PostMapping()
    public void sendOrder(String topic, String msg){
        System.out.println("Producer. topic <"+topic+">"+": "+msg);
        kafkaTemplate().send(topic, msg);

    }


}
