package covidalert.models;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="authorities")
@Access(AccessType.FIELD)
public class Authorities {
    @Id
    private long authority_id;
    private String username;
    private String authority;

    public Authorities(long authority_id, String username, String authority) {
        this.authority_id = authority_id;
        this.username = username;
        this.authority = authority;
    }

    public Authorities() {
    }
}
