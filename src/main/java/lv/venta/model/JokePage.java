package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Joke_TABLE")
@Entity
public class JokePage {


    @Setter(value = AccessLevel.NONE)
    @Column(name = "Idar")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int  idJo;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "Title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "Author")
    private Editor author;

    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "Content")
    private String content;

    @Column(name = "DatePosted")
    private LocalDate datePosted  = LocalDate.now();

    @OneToMany
    private List<Review> review = new ArrayList<>();



    public JokePage( String title, Genre genre, Editor author, String content) {
        setTitle(title);
        setAuthor(author);
        setContent(content);
        setDatePosted(LocalDate.now());
    }

}
