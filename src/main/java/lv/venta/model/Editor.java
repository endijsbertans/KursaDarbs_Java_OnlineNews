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
public class Editor extends Person {

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "field_of_operation")
    private FieldOfOperation fieldOfOperation;


    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Collection<Article> articles;
    @PreRemove
    private void preRemove() {
        for (Article article : articles) {
            article.setAuthor(null);
        }
    }

    public Editor(Person person, FieldOfOperation fieldOfOperation) {
        super(person);
        this.fieldOfOperation = fieldOfOperation;
    }
}
