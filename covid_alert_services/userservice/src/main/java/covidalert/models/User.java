package covidalert.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name="user_entity")
@Access(AccessType.FIELD)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;

    @NotBlank
    private String first_name;
    @NotBlank
    private String last_name;
    @NotBlank
    private String email;

    private String phone_number;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String user_id) {
        this.username = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
