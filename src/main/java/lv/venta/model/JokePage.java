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
@Entity
@Table(name = "Joke_TABLE")
public class JokePage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Idar")
    private int idJo;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "Title")
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Author")
    private Editor author;

    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "Content")
    private String content;

    @Column(name = "DatePosted")
    private LocalDate datePosted = LocalDate.now();



    public JokePage(String title, Editor author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.datePosted = LocalDate.now();
    }

}
