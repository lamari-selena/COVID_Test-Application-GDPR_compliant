package covidtest.repositories;

import covidtest.models.CovidTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class CovidTestRepositoryImpl implements CovidTestCustomRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<CovidTest> getUserCovidTest(String id) {
        String sql = "SELECT c FROM CovidTest c Where c.user_id = :id";
        System.out.println(sql);
        TypedQuery<CovidTest> query = entityManager.createQuery(sql, CovidTest.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
