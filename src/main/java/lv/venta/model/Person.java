package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
public class Person {

    @Setter(value = AccessLevel.NONE)
    @Column(name = "Idp")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long idp;

    @NotNull
    @Column(name = "Name")
    @Pattern(regexp = "[A-ZĒŪĪĻĶĢŠĀČŅ]{1}[a-zēūīļķģšāžčņ]+")
    private String name;

    @NotNull
    @Column(name = "Surname")
    @Pattern(regexp = "[A-ZĒŪĪĻĶĢŠĀČŅ]{1}[a-zēūīļķģšāžčņ]+")
    private String surname;

    @NotNull
    @Column(name = "PersonCode")
    @Pattern(regexp = "\\d{5,6}-\\d{5}")
    private String personCode;



    public Person(String name, String personCode, String surname){
        setName(name);
        setSurname(surname);
        setPersonCode(personCode);
    }

    public Person(Person person) {
        setPersonCode(person.getPersonCode());
        setName(person.getName());
        setSurname(person.getSurname());
    }
}