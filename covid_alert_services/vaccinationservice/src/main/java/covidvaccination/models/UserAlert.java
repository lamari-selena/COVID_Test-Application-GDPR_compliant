package covidvaccination.models;

import java.sql.Timestamp;

public class UserAlert {

    private String user_id;
    private Timestamp location_date;
    private String message = "Vous avez récemment été en contact avec une personne testée positive à la Covid-19";

    public UserAlert(String user_id) {
        this.user_id = user_id;
    }

    public UserAlert(String user_id, Timestamp location_date) {
        this.user_id = user_id;
        this.location_date = location_date;
    }

    public UserAlert() {}

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public Timestamp getLocation_date() {
        return location_date;
    }

    public void setLocation_date(Timestamp location_date) {
        this.location_date = location_date;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserAlert{" +
                "user_id='" + user_id + '\'' +
                ", location_date=" + location_date +
                ", message='" + message + '\'' +
                '}';
    }
}
