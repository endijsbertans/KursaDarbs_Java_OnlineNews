package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Advert_TABLE")
@Entity
public class Advertisement {
    @Setter(value = AccessLevel.NONE)
    @Column(name = "Ida")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long ida;

    @NotNull
    @Column(name = "Title")
    private String title;

    @NotNull
    @Column(name = "Price")
    @Min(0)
    private float price;

    @NotNull
    @Column(name = "Description")
    private String description;

    // AUTORS

    @NotNull
    private LocalDateTime date;

    public Advertisement(String title, float price, String description) {
        setTitle(title);
        setPrice(price);
        setDescription(description);
        setDate(LocalDateTime.now());
    }
}
