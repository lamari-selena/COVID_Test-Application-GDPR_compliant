package covidvaccination.repositories;

import covidvaccination.models.Vaccination;

import java.util.List;

public interface VaccinationCustomRepository {
    List<Vaccination> getUserVaccination(String id);
    public boolean isVaccineted (String id);
    public List<String> checkVaccination (List<String> users);
}
