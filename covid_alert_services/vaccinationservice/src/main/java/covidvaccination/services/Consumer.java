package covidvaccination.services;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import covidvaccination.models.UserAtRisk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class Consumer {
    private static final String TOPIC = "UserAtRiskTopic";

    @Autowired
    private final VaccinationService vaccinationService;

    public Consumer (VaccinationService vaccinationService){
        this.vaccinationService = vaccinationService;
    }

    @KafkaListener(topics = TOPIC, groupId = "group_id")
    public void consume(String json)
    {

        System.out.println("ok");
        Type listOfUserAtRisk = new TypeToken<ArrayList<UserAtRisk>>(){}.getType();
        List<UserAtRisk> filteredList = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        System.out.println("Users At Risk message consumed -> String "+ json);

        try {
            List<UserAtRisk> listUserAtRisk= gson.fromJson(json, listOfUserAtRisk);
            System.out.println("List User At Risk -> " + listUserAtRisk);
            filteredList = vaccinationService.filterUserAtRisk(listUserAtRisk);
            System.out.println("Filtered list User At Risk -> " + filteredList);
            vaccinationService.sendUserAtRiskAlert(filteredList);

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
