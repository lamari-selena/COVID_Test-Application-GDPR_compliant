package covidtest.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDate;


@Entity
@Table(name= "covidtests")
public class CovidTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long covidtest_id;
    private String covidtest_type;
    private String covidtest_result;
    private int covidtest_valid_duration;
    private LocalDate covidtest_date;
    private String user_id;


    public long getCovidtest_id() {
        return covidtest_id;
    }
    public void setCovidtest_id(long covidtest_id) {
        this.covidtest_id = covidtest_id;
    }

    public String getCovidtest_type() {
        return covidtest_type;
    }

    public void setCovidtest_type(String covidtest_type) {
        this.covidtest_type = covidtest_type;
    }

    public String getCovidtest_result() {
        return covidtest_result;
    }

    public void setCovidtest_result(String covidtest_result) {
        this.covidtest_result = covidtest_result;
    }

    public int getCovidtest_valid_duration() {
        return covidtest_valid_duration;
    }

    public void setCovidtest_valid_duration(int covidtest_valid_duration) {
        this.covidtest_valid_duration = covidtest_valid_duration;
    }

    public LocalDate getCovidtest_date() {
        return covidtest_date;
    }

    public void setCovidtest_date(LocalDate covidtest_date) {
        this.covidtest_date = covidtest_date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    protected CovidTest(){}

    public CovidTest(String covidtest_type, String covidtest_result, int covidtest_valid_duration, LocalDate covidtest_date, String user_id) {
            this.covidtest_type = covidtest_type;
            this.covidtest_result = covidtest_result;
            this.covidtest_valid_duration = covidtest_valid_duration;
            this.covidtest_date = covidtest_date;
            this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "CovidTest{" +
                "covidtest_id=" + covidtest_id +
                ", covidtest_type='" + covidtest_type +
                ", covidtest_result=" + covidtest_result +
                ", covidtest_valid_duration=" + covidtest_valid_duration +
                ", covidtest_date=" + covidtest_date +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}

