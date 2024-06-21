package lv.venta.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@NoArgsConstructor
public class RegisteredUser extends MyUser {

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Collection<Advertisement> adverts;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Collection<Event> events;

    @PreRemove
    private void preRemove() {
        for (Advertisement adv : adverts) {
            adv.setAuthor(null);
        }
        for (Event event : events) {
            event.setAuthor(null);
        }
    }

    public RegisteredUser(String username, String password) {
        super(username, password);
    }

    public RegisteredUser(MyUser myUser) {
        super(myUser.getUsername(), myUser.getPassword());
        this.setId(myUser.getId());
        this.setAuthorities(myUser.getAuthorities());
    }
}
