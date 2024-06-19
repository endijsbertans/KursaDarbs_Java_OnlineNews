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
@Table(name = "Article_TABLE")
@Entity
public class Article {


    @Setter(value = AccessLevel.NONE)
    @Column(name = "Idar")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int  idar;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "Title")
    private String title;

    @NotNull
    @Column(name = "Genre")
    private Genre genre;


    //pagaidam jagaida uz rudzu
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



    public Article( String title, Genre genre, Editor author, String content) {
        setTitle(title);
        setGenre(genre);
        setAuthor(author);
        setContent(content);
        setDatePosted(LocalDate.now());
    }






}
