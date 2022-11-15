package covidlocation.repositories;

import covidlocation.models.Location;
import covidlocation.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface LocationCustomRepository {

    List<Location> getUserLocation(String id);
    //List<String> getNearUserByLocation(Location location);
    //Set<String> getNearUser(List<Location> location);



}