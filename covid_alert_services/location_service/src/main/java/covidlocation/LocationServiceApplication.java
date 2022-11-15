package covidlocation;

import covidlocation.models.Location;
import covidlocation.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
@EnableEurekaClient
public class LocationServiceApplication {
    public static void main(String[] args) { SpringApplication.run(LocationServiceApplication.class, args); }

}
