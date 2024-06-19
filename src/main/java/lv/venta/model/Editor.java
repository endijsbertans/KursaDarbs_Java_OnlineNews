package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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


//    @OneToMany(mappedBy = "editor", cascade = CascadeType.ALL)
//    private List<Article> articles;

    public Editor(Person person, FieldOfOperation fieldOfOperation) {
        super(person);
        this.fieldOfOperation = fieldOfOperation;
    }
}
