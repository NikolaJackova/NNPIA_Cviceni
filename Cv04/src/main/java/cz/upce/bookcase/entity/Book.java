package cz.upce.bookcase.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String Isbn;

    private Integer publishYear;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private Set<BookAuthor> bookAuthors;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "BOOK_GENRE", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> bookGenres;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return Isbn;
    }

    public void setIsbn(String isbn) {
        Isbn = isbn;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    public Set<BookAuthor> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(Set<BookAuthor> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }

    public Set<Genre> getBookGenres() {
        return bookGenres;
    }

    public void setBookGenres(Set<Genre> bookGenres) {
        this.bookGenres = bookGenres;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void addGenre(Genre genre){
        if (bookGenres == null){
            bookGenres = new HashSet<Genre>();
        }
        bookGenres.add(genre);
    }
}
