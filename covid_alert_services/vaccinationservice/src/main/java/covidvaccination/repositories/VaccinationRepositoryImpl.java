package covidvaccination.repositories;

import covidvaccination.models.Vaccination;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class VaccinationRepositoryImpl implements VaccinationCustomRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Vaccination> getUserVaccination(String id) {
        String sql = "SELECT v FROM Vaccination v Where v.user_id = :id";
        System.out.println(sql);
        TypedQuery<Vaccination> query = entityManager.createQuery(sql, Vaccination.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
    @Override
    public boolean isVaccineted (String id) {
        boolean isVaccineted = false;
        List vaccines = getUserVaccination(id);
        int nbOfVaccines = vaccines.size();
        if (nbOfVaccines == 2){
            isVaccineted = true;
        }
        return isVaccineted;
    }
    @Override
    public List<String> checkVaccination (List<String> users){
        List<String> userNotVaccinated = new ArrayList<>();
        for (int i = 0; i<users.size(); i++){
            String user = users.get(i);
            if (!isVaccineted(user)){
                userNotVaccinated.add(user);
            }
        }
        return userNotVaccinated;
    }


}
