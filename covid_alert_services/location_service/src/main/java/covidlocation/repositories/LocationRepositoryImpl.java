package covidlocation.repositories;

import covidlocation.models.Location;
import covidlocation.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LocationRepositoryImpl implements LocationCustomRepository {

    private final double COEFFICIENT = 69.1;
    private final double DENOMINATOR = 57.3;


    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Location> getUserLocation(String id) {
        String sql = "SELECT loc FROM Location loc Where user_id = :id";
        TypedQuery<Location> query = entityManager.createQuery(sql, Location.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
/*
    @Override
    public List<String> getNearUserByLocation(Location location) {
        String sql = "SELECT user_id FROM (SELECT user_id,longitude,latitude, SQRT( POW(:coefficient * (latitude - :start_latitude), 2) + POW(:coefficient * (:start_longitude - longitude) * COS(latitude / :denominator), 2)) AS distance,(CAST(now() as date) - CAST(location_date as date) ) AS nb_days FROM Locations ORDER BY distance) location_distance WHERE distance < :max_distance and nb_days< :contagion_time";
        String hql = "SELECT loc, SQRT( POW(69.1 * (latitude - :start_latitude), 2) + POW(69.1 * (:start_longitude - longitude) * COS(latitude / 57.3), 2)) AS distance FROM Locations loc WHERE distance < :max_distance ORDER BY distance";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("start_latitude", location.getLatitude());
        query.setParameter("start_longitude", location.getLongitude());
        query.setParameter("max_distance", MAX_DISTANCE);
        query.setParameter("coefficient", COEFFICIENT);
        query.setParameter("denominator", DENOMINATOR);
        query.setParameter("contagion_time", CONTAGION_TIME);

        System.out.println(query.getResultList());


        return query.getResultList();
    }

    @Override
    public Set<String> getNearUser(List<Location> locations) {
        Set<String> nearUsers = new LinkedHashSet<>();
        for (Location location :locations){
            if (!this.getNearUserByLocation(location).isEmpty())
            nearUsers.addAll(this.getNearUserByLocation(location));
        }
        return nearUsers;
    }

 */

    public User getUser(long id) {
        String sql = "SELECT us FROM User us Where user_id = :id";
        TypedQuery<User> query = entityManager.createQuery(sql, User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }


    /* Code pour kafka */

    public List<Location> locations ;


    public List<Location> getUserLocationFromKafka(String id) {
        Predicate<Location> byLocation = location -> location.getUser_id() == id;
        List<Location> result = locations.stream().filter(byLocation)
                .collect(Collectors.toList());

        return result;

    }

    public List<String> getNearUserByLocationKafka(Location location,long MAX_DISTANCE,long CONTAGION_TIME) {
        List<String> users = new ArrayList<>();
        for (Location loc :locations){
            if (loc.isCloseto(location,MAX_DISTANCE) && loc.isMoreRecentThan(CONTAGION_TIME))
                users.add(location.getUser_id());
        }
        return  users;
    }

    public Set<String> getNearUserKafka(List<Location> locations,long MAX_DISTANCE,long CONTAGION_TIME) {
        Set<String> nearUsers = new LinkedHashSet<>();
        for (Location location :locations){
            nearUsers.addAll(this.getNearUserByLocationKafka(location,MAX_DISTANCE,CONTAGION_TIME));
        }
        return nearUsers;
    }





}