package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class Editor extends MyUser {

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "field_of_operation")
    private FieldOfOperation fieldOfOperation;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Collection<Article> articles = new ArrayList<>();

    @PreRemove
    private void preRemove() {
        for (Article article : articles) {
            article.setAuthor(null);
        }
    }

    public Editor(String name, String surname, String username, String password, FieldOfOperation fieldOfOperation, MyAuthority... auths) {
        super(name, surname, username, password, auths);
        this.fieldOfOperation = fieldOfOperation;
    }
}
