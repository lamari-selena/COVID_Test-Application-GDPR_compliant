package covidlocation.repositories;

import covidlocation.models.Location;
import covidlocation.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface LocationRepository extends JpaRepository<Location,Long>,LocationCustomRepository {





}
