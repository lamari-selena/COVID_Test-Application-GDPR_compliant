package covidvaccination.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name= "vaccinations")
public class Vaccination {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vaccination_id;
    private String vaccine_name;
    private String vaccine_type;
    private String target_disease;
    private String vaccination_center;
    private String country;
    private int number_of_doses;
    private LocalDate vaccination_date;
    private String user_id;

    public long getVaccination_id() {
        return vaccination_id;
    }

    public void setVaccination_id(long vaccination_id) {
        this.vaccination_id = vaccination_id;
    }

    public String getVaccine_name() {
        return vaccine_name;
    }

    public void setVaccine_name(String vaccination_name) {
        this.vaccine_name = vaccination_name;
    }

    public String getVaccine_type() {
        return vaccine_type;
    }

    public void setVaccine_type(String vaccine_type) {
        this.vaccine_type = vaccine_type;
    }

    public String getTarget_disease() {
        return target_disease;
    }

    public void setTarget_disease(String target_disease) {
        this.target_disease = target_disease;
    }

    public String getVaccination_center() {
        return vaccination_center;
    }

    public void setVaccination_center(String vaccination_center) {
        this.vaccination_center = vaccination_center;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNumber_of_doses() {
        return number_of_doses;
    }

    public void setNumber_of_doses(int number_of_doses) {
        this.number_of_doses = number_of_doses;
    }

    public LocalDate getVaccination_date() {
        return vaccination_date;
    }

    public void setVaccination_date(LocalDate vaccination_date) {
        this.vaccination_date = vaccination_date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    protected Vaccination(){

    }

    public Vaccination(String vaccine_name, String vaccine_type, String target_disease, String vaccination_center, String country, int number_of_doses, LocalDate vaccination_date, String user_id) {
        this.vaccine_name = vaccine_name;
        this.vaccine_type = vaccine_type;
        this.target_disease = target_disease;
        this.vaccination_center = vaccination_center;
        this.country = country;
        this.number_of_doses = number_of_doses;
        this.vaccination_date = vaccination_date;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Vaccination{" +
                "vaccination_id=" + vaccination_id +
                ", vaccine_name='" + vaccine_name + '\'' +
                ", vaccine_type='" + vaccine_type + '\'' +
                ", target_disease='" + target_disease + '\'' +
                ", vaccination_center='" + vaccination_center + '\'' +
                ", country='" + country + '\'' +
                ", number_of_doses=" + number_of_doses +
                ", vaccination_date=" + vaccination_date +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
