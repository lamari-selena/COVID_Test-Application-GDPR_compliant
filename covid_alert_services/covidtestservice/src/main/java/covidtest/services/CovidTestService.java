package covidtest.services;

import covidtest.models.CovidTest;
import covidtest.models.PositiveUser;
import covidtest.repositories.CovidTestRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;

@Service
@Transactional
public class CovidTestService {

    private CovidTestRepository covidTestRepository;

    private final Producer producer;

    @Autowired
    public CovidTestService(CovidTestRepository covidTestRepository, Producer producer){
        this.covidTestRepository = covidTestRepository;
        this.producer = producer;
    }

    public CovidTest addCovidTest(CovidTest covidTest){
        return covidTestRepository.saveAndFlush(covidTest);
    }

    public List<CovidTest> findAllCovidTest(){
        return covidTestRepository.findAll();
    }


    public CovidTest findCovidTestById(Long id){
        if(covidTestRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CovidTest with ID "+id+" not found");
        }
        return covidTestRepository.findById(id).get();
    }

    public void deleteCovidTest(Long id){
        if(covidTestRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CovidTest with ID "+id+" not found");
        }
        covidTestRepository.deleteById(id);
    }

    public CovidTest updateCovidTest(Long id, CovidTest covidTest) {
        if (covidTest.getCovidtest_type().isEmpty() || covidTest.getCovidtest_result().isEmpty() || covidTest.getCovidtest_date() == null || covidTest.getUser_id() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "attributes from covidTest are missing");
        }
        if(covidTestRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CovidTest with ID "+id+" not found");
        }
        CovidTest existingCovidTest = covidTestRepository.findById(id).get();
        BeanUtils.copyProperties(covidTest,existingCovidTest,"covidtest_id");
        return covidTestRepository.saveAndFlush(existingCovidTest);
    }

    public List<CovidTest> findCovidTestByUser(String id){
        try {
            return covidTestRepository.getUserCovidTest(id);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID "+id+" not found");
        }
    }

    public boolean covidTestPositif(CovidTest covidTest){
        boolean result = false;
        System.out.println(covidTest.getCovidtest_result());
        if (covidTest.getCovidtest_result().toLowerCase(Locale.ROOT).equals("positif")){
            result = true;
        }
        return result;
    }

    public void verificationCovidTest(CovidTest covidTest){
        System.out.println("verificationCovidTest");
        System.out.println(covidTest);
        boolean testPositif = covidTestPositif(covidTest);

        if (testPositif){
            System.out.println("positif");
            PositiveUser positiveUser = new PositiveUser(covidTest.getUser_id());
            System.out.println(positiveUser);
            producer.sendPositiveUserMessage(positiveUser);
        }
    }

//    public void post (PositiveUser positiveUser){
//        producer.sendPositiveUserMessage(positiveUser);
//    }

}
