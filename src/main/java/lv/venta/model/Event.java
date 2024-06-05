package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Column(name = "startDate")
    private LocalDateTime startDate;

    @NotNull
    @Min(0)
    @Max(365)
    private int startTimeInDays;

    public Event(String title, float price, String description, int startTimeInDays) {
        setTitle(title);
        setPrice(price);
        setDescription(description);
        setStartDate(LocalDateTime.now().plusDays(startTimeInDays));
    }
}
