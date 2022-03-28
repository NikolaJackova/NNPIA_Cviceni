package cz.upce.bookcase.entity;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer genreId;

    @Column(nullable = false, unique = true)
    @ColumnTransformer(write = "UPPER(?)", read = "UPPER(name)")
    private String name;

    @ManyToMany(mappedBy = "bookGenres", fetch=FetchType.LAZY)
    private Set<Book> genreBooks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getGenreBooks() {
        return genreBooks;
    }

    public void setGenreBooks(Set<Book> genreBooks) {
        this.genreBooks = genreBooks;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }
}
