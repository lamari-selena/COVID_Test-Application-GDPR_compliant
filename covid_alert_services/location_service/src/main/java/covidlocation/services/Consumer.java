package covidlocation.services;

import covidlocation.models.Location;
import covidlocation.models.PositiveUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class Consumer {
    private static List<Location> locations = new ArrayList<>();
    private static final String TOPIC = "PositiveUsersTopic";

    @Autowired
    private final SendService sendService;

    public Consumer(SendService sendService) {
        this.sendService = sendService;
    }


    public static List<Location> getLocations() {
        return locations;
    }

    @KafkaListener(topics = TOPIC, groupId = "group_id")
    public void consume(String json)
    {
        Gson gson = new GsonBuilder().create();
        System.out.println("Positive user message consumed -> String "+ json);
        try {
            PositiveUser positiveUser1 = gson.fromJson(json, PositiveUser.class);
            System.out.println("Casted received message" + positiveUser1.getUser_id());
            sendService.findUsersAtRisk(positiveUser1.getUser_id(),this.getLocations());

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    @KafkaListener(topicPartitions =
            {@TopicPartition(topic = "location_topic",
                    partitionOffsets = @PartitionOffset(partition="0", initialOffset = "0"))
            })
    public void consumeAllLocation(Location location)
    {
        //Gson gson = new GsonBuilder().create();
        //System.out.println("Location message consumed -> String "+ location);

        try {
           // Location location1 = gson.fromJson(location, Location.class);
            System.out.println("Location object :" + location);
            System.out.println("Location toString :" + location.getLatitude());
            locations.add(location);
            System.out.println(locations.toString());

        } catch(Exception e) {
            System.out.println(e);
        }


    }

}
