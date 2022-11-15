package covidlocation.services;

import covidlocation.models.Location;
import covidlocation.models.UserAtRisk;
import covidlocation.repositories.LocationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import covidlocation.services.Producer;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional
public class LocationService {


    private LocationRepository locationRepository;
    private final Producer producer;
    private final Consumer consumer;

    //private final int MAX_DISTANCE = 20000;
    //private final int CONTAGION_TIME = 7;



    @Autowired
    public LocationService(LocationRepository locationRepository, Producer producer,Consumer consumer){
        this.locationRepository = locationRepository;
        this.producer = producer;
        this.consumer = consumer;
    }


    public Location addLocation(Location location) {
        this.producer.saveCreateLocationLog(location);
        return  locationRepository.saveAndFlush(location);
    }

    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }


    public Location findLocationByID(Long id) {
        if(locationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location with ID "+id+" not found");
        }
        return locationRepository.findById(id).get();
    }


    public void deleteLocation(Long id) {
        if(locationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location with ID "+id+" not found");
        }
        locationRepository.deleteById(id);
    }


    public Location updateLocation(Long id,Location location) {
        if(locationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location with ID "+id+" not found");
        }
        Location existingUser = locationRepository.findById(id).get();
        BeanUtils.copyProperties(location,existingUser,"location_id");
        return locationRepository.saveAndFlush(existingUser);
    }

    public List<Location> findLocationByUser(String id) {
        try {
            return locationRepository.getUserLocation(id);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID "+id+" not found");
        }
    }
/*
    public Set<UserAtRisk> findUsersAtRisk(String id) {
        try {
            List<Location> locations = this.getUserLocationFromKafka(id);
            System.out.println("getUserLocationFromKafka :" + id + ", locations:"+ locations.toString());
            Set<UserAtRisk> usersAtRisk = this.getNearUserKafka(locations);
            List<UserAtRisk> list = new ArrayList<UserAtRisk>( usersAtRisk );
            producer.sendUserAtRiskMessage(list);
            return usersAtRisk;
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw exception;

        }
    }

    public List<Location> getUserLocationFromKafka(String id) {
        List<Location> locations = consumer.getLocations();
        Predicate<Location> byLocation = location -> location.getUser_id().equals(id) ;
        List<Location> result = locations.stream().filter(byLocation)
                .collect(Collectors.toList());

        return result;

    }

    public List<UserAtRisk> getNearUserByLocationKafka(Location location) {
        List<Location> locations = consumer.getLocations();
        System.out.println("consumer locations  : " + locations.toString());

        List<UserAtRisk> usersAtRisk = new ArrayList<>();
        for (Location loc :locations){
           if (loc.isCloseto(location,MAX_DISTANCE) && loc.isMoreRecentThan(CONTAGION_TIME)) {
               usersAtRisk.add(new UserAtRisk(loc.getUser_id(),loc.getLocation_date()));
           }
        }
        System.out.println("getNearUserByLocationKafka : " + usersAtRisk.toString());
        return  usersAtRisk;
    }

    public Set<UserAtRisk> getNearUserKafka(List<Location> locations) {
        Set<UserAtRisk> nearUsers = new LinkedHashSet<>();
        for (Location location :locations){
            nearUsers.addAll(this.getNearUserByLocationKafka(location));
        }
        return nearUsers;
    }

 */



}
