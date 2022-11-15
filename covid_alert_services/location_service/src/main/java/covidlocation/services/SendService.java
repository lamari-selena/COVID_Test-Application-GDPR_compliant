package covidlocation.services;


import covidlocation.models.Location;
import covidlocation.models.UserAtRisk;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional
public class SendService {
    private final double MAX_DISTANCE = 0.0027; // 300 meters
    private final int CONTAGION_TIME = 7;
    private final Producer producer;

    public SendService(Producer producer) {
        this.producer = producer;
    }


    public Set<UserAtRisk> findUsersAtRisk(String id,List<Location> allLocations) {
        try {
            List<Location> locations = this.getUserLocationFromKafka(id,allLocations);
            System.out.println("getUserLocationFromKafka :" + id + ", locations:"+ locations.toString());
            Set<UserAtRisk> usersAtRisk = this.getNearUserKafka(locations,allLocations);
            List<UserAtRisk> list = new ArrayList<UserAtRisk>( usersAtRisk );
            System.out.println("################################################");
            System.out.println(list);
            System.out.println("################################################");
            producer.sendUserAtRiskMessage(list);
            return usersAtRisk;
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw exception;

        }
    }

    public List<Location> getUserLocationFromKafka(String id,List<Location> locations) {
        Predicate<Location> byLocation = location -> location.getUser_id().equals(id) ;
        List<Location> result = locations.stream().filter(byLocation)
                .collect(Collectors.toList());

        return result;

    }

    public List<UserAtRisk> getNearUserByLocationKafka(Location location,List<Location> locations) {

        System.out.println("consumer locations  : " + locations.toString());

        List<UserAtRisk> usersAtRisk = new ArrayList<>();
        for (Location loc :locations){
            if (loc.isCloseto(location,MAX_DISTANCE) && loc.isMoreRecentThan(CONTAGION_TIME) && !loc.getUser_id().equals(location.getUser_id())) {
                usersAtRisk.add(new UserAtRisk(loc.getUser_id(),loc.getLocation_date()));
            }
        }
        System.out.println("getNearUserByLocationKafka : " + usersAtRisk.toString());
        return  usersAtRisk;
    }

    public Set<UserAtRisk> getNearUserKafka(List<Location> locations,List<Location> allLocations) {
        Set<UserAtRisk> nearUsers = new LinkedHashSet<>();
        for (Location location :locations){
            nearUsers.addAll(this.getNearUserByLocationKafka(location,allLocations));
        }
        return nearUsers;
    }

}
