package lv.venta.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Review_TABLE")
@Entity
public class Review {


    @Setter(value = AccessLevel.NONE)
    @Column(name = "Idre")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long idre;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "Message")
    private String message;

    @Column(name = "DatePosted")
    private LocalDate datePosted  = LocalDate.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Idar")
    private Article article;


    // User jagaida uz Rudolfu


    public Review(String message, Article article){
        setMessage(message);
        setArticle(article);
        setDatePosted(LocalDate.now());
    }



}
