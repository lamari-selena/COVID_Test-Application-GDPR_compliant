package covidtest.repositories;

import covidtest.models.CovidTest;

import java.util.List;

public interface CovidTestCustomRepository {
    List<CovidTest> getUserCovidTest(String id);
}
