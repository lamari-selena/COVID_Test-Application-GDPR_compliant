package covidtest.services;

import com.google.gson.GsonBuilder;
import covidtest.models.PositiveUser;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;

@Service
public class Producer {
    private static final String TOPIC = "PositiveUsersTopic";
    @Autowired
    private KafkaTemplate<String, Object> kafkaLocationTemplate;

    public void sendPositiveUserMessage(PositiveUser positiveUser){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(positiveUser);
        System.out.println("send message : "+ json);
        this.kafkaLocationTemplate.send(TOPIC,json);
    }

    @Bean
    public NewTopic createTopic(){
        return new NewTopic(TOPIC,3,(short) 1);
    }


    public void saveCreateLocationLog(PositiveUser positiveUser) {
        System.out.println("Positive user message created -> "+ positiveUser);
        this.sendPositiveUserMessage(positiveUser);
    }
}
