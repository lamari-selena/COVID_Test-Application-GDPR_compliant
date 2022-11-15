package covidlocation.models;

import java.sql.Timestamp;
import java.util.Objects;

public class UserAtRisk {
    private String user_id;
    private Timestamp location_date;

    public UserAtRisk(String user_id,Timestamp location_date) {

        this.user_id = user_id;
        this.location_date = location_date;
    }

    public UserAtRisk() {}

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "UserAtRisk{" +
                "user_id='" + user_id + '\'' +
                ", location_date=" + location_date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAtRisk that = (UserAtRisk) o;
        return Objects.equals(user_id, that.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, location_date);
    }
}
