package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Editor extends Person {

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Field of Operation is required")
    @Column(name = "field_of_operation")
    private FieldOfOperation fieldOfOperation;

    // Constructor including the inherited fields
    public Editor(String name, String surname, FieldOfOperation fieldOfOperation) {
        super(name, surname);
        this.fieldOfOperation = fieldOfOperation;
    }
}
