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
@Table(name = "Event_TABLE")
@Entity
public class Event {
    @Setter(value = AccessLevel.NONE)
    @Column(name = "Ide")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long ide;

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
    private LocalDateTime startDate;

    public Event(String title, float price, String description, LocalDateTime date) {
        setTitle(title);
        setPrice(price);
        setDescription(description);
        setStartDate(date);
    }
}
