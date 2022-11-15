package covidtest.repositories;

import covidtest.models.CovidTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CovidTestRepository extends JpaRepository<CovidTest,Long>, CovidTestCustomRepository {
}
