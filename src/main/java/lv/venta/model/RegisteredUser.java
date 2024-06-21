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
<<<<<<< HEAD
public class RegisteredUser extends MyUser {

=======
public class RegisteredUser extends MyUser{
>>>>>>> Endijs
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
<<<<<<< HEAD

    public RegisteredUser(String username, String password) {
        super(username, password);
=======
    public RegisteredUser(String name, String surname, MyUser user) {
        super(name, surname, user.getUsername(), user.getPassword());
>>>>>>> Endijs
    }

    public RegisteredUser(MyUser myUser) {
        super(myUser.getUsername(), myUser.getPassword());
        this.setId(myUser.getId());
        this.setAuthorities(myUser.getAuthorities());
    }
}
