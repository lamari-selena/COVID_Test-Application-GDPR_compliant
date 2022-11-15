package covidvaccination.repositories;

import covidvaccination.models.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface VaccinationRepository extends JpaRepository<Vaccination,Long>, VaccinationCustomRepository {
}
