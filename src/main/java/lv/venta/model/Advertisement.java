package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 3, max = 50)
    @Column(name = "Title")
    private String title;

    @NotNull
    @Column(name = "Price")
    @Min(0)
    private float price;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "Description")
    private String description;

    // AUTORS
    @Column(name = "Date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "Author")
    private RegisteredUser author;

    public Advertisement(String title, float price, String description, RegisteredUser author) {
        setAuthor(author);
        setTitle(title);
        setPrice(price);
        setDescription(description);
        setDate(LocalDateTime.now());
    }
}
