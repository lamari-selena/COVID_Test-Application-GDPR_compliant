package covidvaccination.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import covidvaccination.models.UserAlert;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Producer {
    private static final String TOPIC = "UserAlertTopic";

    @Autowired
    private KafkaTemplate<String, Object> kafkaUserAlertTemplate;

    public void sendUserAlert(List<UserAlert> listUserAlert){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(listUserAlert);
        this.kafkaUserAlertTemplate.send(TOPIC,json);
    }

    @Bean
    public NewTopic createTopic(){
        return new NewTopic(TOPIC,3,(short) 1);
    }

//    public void saveCreateLocationLog(UserAlert userAlert) {
//        System.out.println("UserAlert message created -> "+ userAlert);
//        this.sendUserAlert(userAlert);
//    }
}