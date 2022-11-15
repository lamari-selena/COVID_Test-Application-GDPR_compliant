package covidvaccination.services;

import covidvaccination.models.UserAlert;
import covidvaccination.models.UserAtRisk;
import covidvaccination.models.Vaccination;
import covidvaccination.repositories.VaccinationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class VaccinationService {
    private VaccinationRepository vaccinationRepository;

    private final Producer producer;

    @Autowired
    public VaccinationService(VaccinationRepository vaccinationRepository, Producer producer){
        this.vaccinationRepository = vaccinationRepository;
        this.producer = producer;
    }

    public Vaccination addVaccination(Vaccination vaccination){
        return vaccinationRepository.saveAndFlush(vaccination);
    }

    public List<Vaccination> findAllVaccination(){
        return vaccinationRepository.findAll();
    }


    public Vaccination findVaccinationById(Long id){
        if(vaccinationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaccination with ID "+id+" not found");
        }
        return vaccinationRepository.findById(id).get();
    }

    public void deleteVaccination(Long id){
        if(vaccinationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaccination with ID "+id+" not found");
        }
        vaccinationRepository.deleteById(id);
    }

    public Vaccination updateVaccination(Long id, Vaccination vaccination) {
        if(vaccinationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaccination with ID "+id+" not found");
        }
        if (vaccination.getVaccination_date() == null || vaccination.getVaccination_center().isEmpty() || vaccination.getCountry() == null || vaccination.getTarget_disease().isEmpty() || vaccination.getUser_id().isEmpty() || vaccination.getVaccine_name().isEmpty() || vaccination.getVaccine_type().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "attributes from vaccination are missing");
        }
        Vaccination existingVaccination = vaccinationRepository.findById(id).get();
        BeanUtils.copyProperties(vaccination,existingVaccination,"vaccination_id");
        return vaccinationRepository.saveAndFlush(existingVaccination);
    }

    public List<Vaccination> findVaccinationByUser(String id){
        try {
            return vaccinationRepository.getUserVaccination(id);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID "+id+" not found");
        }
    }

    public boolean isUserFullVaccinated (String id){
        try {
            return vaccinationRepository.isVaccineted(id);
        }
        catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID "+id+" not found");
        }
    }

    public List<UserAtRisk> filterUserAtRisk(List<UserAtRisk> listUserAtRisk){
        List<UserAtRisk> filteredList = new ArrayList<>();
        for (UserAtRisk userAtRisk : listUserAtRisk){
            if (!isUserFullVaccinated(userAtRisk.getUser_id())){
                filteredList.add(userAtRisk);
            }
        }

        return filteredList;
    }

    public ArrayList<UserAlert> convertToListUserAlert (List<UserAtRisk> listUserAtRisk){

        ArrayList<UserAlert> listUserAlert = new ArrayList<>();

        for (UserAtRisk userAtRisk : listUserAtRisk){
            listUserAlert.add(new UserAlert(userAtRisk.getUser_id(),userAtRisk.getLocation_date()));
        }

        return listUserAlert;
    }

    public void sendUserAtRiskAlert (List<UserAtRisk> listUserAtRisk){

        // Convertis ma liste de UserAtRisk en liste de UserAlert
        List<UserAlert> listUserAlert = new ArrayList<>();
        listUserAlert = convertToListUserAlert(listUserAtRisk);

        try {
            producer.sendUserAlert(listUserAlert);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
