package cz.upce.bookcase.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.util.Objects;

@Entity
public class BookAuthor {

    @EmbeddedId
    private BookAuthorId bookAuthorid;

    @ManyToOne
    @MapsId("bookId")
    private Book book;

    @ManyToOne
    @MapsId("authorId")
    private Author author;

    public BookAuthorId getBookAuthorid() {
        return bookAuthorid;
    }

    public void setBookAuthorid(BookAuthorId bookAuthorid) {
        this.bookAuthorid = bookAuthorid;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        BookAuthor that = (BookAuthor) o;
        return Objects.equals(book, that.book) &&
                Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, author);
    }
}
