package covidtest.controllers;

import covidtest.models.CovidTest;
import covidtest.models.PositiveUser;
import covidtest.services.CovidTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/covidtests")
public class CovidTestController {

    @Autowired
    private final CovidTestService covidTestService;

    @Autowired
    public CovidTestController(CovidTestService covidTestService) {
        this.covidTestService = covidTestService;
    }

    @GetMapping
    public List<CovidTest> list() {
        return covidTestService.findAllCovidTest();
    }

    @GetMapping
    @RequestMapping("{id}")
    public CovidTest get(@PathVariable Long id) {
        return covidTestService.findCovidTestById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CovidTest create(@RequestBody CovidTest covidTest) {
        covidTestService.verificationCovidTest(covidTest);
        System.out.println("ok");
        return  covidTestService.addCovidTest(covidTest);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        covidTestService.deleteCovidTest(id);
    }

    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public CovidTest update(@PathVariable Long id, @RequestBody CovidTest covidtest) {
        return covidTestService.updateCovidTest(id,covidtest);
    }

    @RequestMapping("by_user/{id}")
    public List<CovidTest> byUser(@PathVariable("id") String id) {
        return covidTestService.findCovidTestByUser(id);
    }

//    @RequestMapping("postmessage")
//    public void post(PositiveUser positiveUser) {
//        System.out.println(positiveUser);
//        covidTestService.post(positiveUser);
//    }

}
