package lv.venta.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class RegisteredUser extends Person{
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
    public RegisteredUser(Person person) {
        super(person);
    }

}
