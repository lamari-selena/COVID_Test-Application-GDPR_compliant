package covidlocation.models;


public class PositiveUser {

    private String user_id;

    public PositiveUser(String user_id) {
        this.user_id = user_id;
    }

    public PositiveUser() {}

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "PositiveUser{" +
                "user_id=" + user_id +
                '}';
    }
}