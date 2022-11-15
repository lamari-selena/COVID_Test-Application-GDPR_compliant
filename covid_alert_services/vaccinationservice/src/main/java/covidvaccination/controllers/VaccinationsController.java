package covidvaccination.controllers;

import covidvaccination.models.UserAlert;
import covidvaccination.models.Vaccination;
import covidvaccination.repositories.VaccinationRepository;
import covidvaccination.services.Producer;
import covidvaccination.services.VaccinationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/vaccinations")
public class VaccinationsController {

    @Autowired
    private final VaccinationService vaccinationService;

    private final Producer producer;

    public VaccinationsController(VaccinationService vaccinationService, Producer producer) {
        this.producer = producer;
        this.vaccinationService = vaccinationService;
    }

    @GetMapping
    public List<Vaccination> list() {
        return vaccinationService.findAllVaccination();
    }


    @GetMapping
    @RequestMapping("{id}")
    public Vaccination get(@PathVariable Long id) {
        return vaccinationService.findVaccinationById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vaccination create(@RequestBody Vaccination vaccination) {
        return  vaccinationService.addVaccination(vaccination);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        vaccinationService.deleteVaccination(id);
    }

    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public Vaccination update(@PathVariable Long id, @RequestBody Vaccination vaccination) {
        return vaccinationService.updateVaccination(id, vaccination);
    }

    @RequestMapping("by_user/{id}")
    public List<Vaccination> byUser(@PathVariable("id") String id) {
        return vaccinationService.findVaccinationByUser(id);
    }

    @RequestMapping("is_vaccinated/{id}")
    public boolean isVaccinated (@PathVariable("id") String id){
        return vaccinationService.isUserFullVaccinated(id);
    }

//    @PostMapping("post_message")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createNotification() {
//        producer.sendUserAlert(new UserAlert("243"));
//    }

}
