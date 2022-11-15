package models;


import covidlocation.models.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import java.sql.Timestamp;
import java.time.LocalDateTime;

class LocationTest {

    private final double MAX_DISTANCE = 0.0027;


    @Test
    void shouldNotConsiderDateAsAtRisk() throws ParseException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime old = now.minusWeeks(2);
        Timestamp timestampOld = Timestamp.valueOf(old);
        Location location1 = new Location(1,0,0,timestampOld,"jean");
        Assertions.assertFalse(location1.isMoreRecentThan(7));
    }


    @Test
    void shouldConsiderDateAsAtRisk() throws ParseException {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        Location location1 = new Location(1,0,0,timestamp,"jean");
        Assertions.assertTrue(location1.isMoreRecentThan(7));
    }

    @Test
    void shouldCheckLocationIsClose(){
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        Location location1 = new Location(1,0,0,timestamp,"jean");
        Location location2 = new Location(1,0,0,timestamp,"eric");
        Assertions.assertTrue(location1.isCloseto(location2,MAX_DISTANCE));

    }

    @Test
    void shouldCheckLocationIsNotClose(){
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        Location location1 = new Location(1,43.6343212,3.862357,timestamp,"jean");
        Location location2 = new Location(2,43.5875852,3.9409124,timestamp,"eric");
        Assertions.assertFalse(location1.isCloseto(location2,MAX_DISTANCE));

    }






}
