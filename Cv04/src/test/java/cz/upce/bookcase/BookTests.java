package cz.upce.bookcase;

import cz.upce.bookcase.entity.Book;
import cz.upce.bookcase.entity.Genre;
import cz.upce.bookcase.repository.BookRepository;
import cz.upce.bookcase.repository.GenreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void createBookTest() {
        Book newBook = new Book();
        newBook.setName("Testovací kniha");
        newBook.setIsbn("Neplatné ISBN");
        newBook.setPublishYear(2022);

        bookRepository.save(newBook);
        Assertions.assertEquals(newBook, bookRepository.findByName("Testovací kniha").get());
    }

    @Test
    void updateBookTest(){
        Book newBook = new Book();
        newBook.setName("Testovací kniha");
        newBook.setIsbn("Neplatné ISBN");
        newBook.setPublishYear(2022);

        bookRepository.save(newBook);

        newBook.setPublishYear(2020);
        bookRepository.save(newBook);

        Assertions.assertEquals(2020, bookRepository.findByName("Testovací kniha").get().getPublishYear());
    }

    @Test
    void deleteBookTest(){
        Book newBook = new Book();
        newBook.setName("Testovací kniha");
        newBook.setIsbn("Neplatné ISBN");
        newBook.setPublishYear(2022);

        bookRepository.save(newBook);
        bookRepository.deleteBookByName("Testovací kniha");
        Assertions.assertEquals(true, bookRepository.findByName("Testovací kniha").isEmpty());
    }

    @Test
    void createBookWithGenre(){
        Genre newGenre = new Genre();
        newGenre.setName("Testovací žánr");
        genreRepository.save(newGenre);

        Book newBook = new Book();
        newBook.setName("Testovací kniha");
        newBook.setIsbn("Neplatné ISBN");
        newBook.setPublishYear(2022);
        newBook.addGenre(newGenre);
        bookRepository.save(newBook);

        Optional<Book> books = bookRepository.findByName("Testovací kniha");
        Assertions.assertEquals(true, books.get().getBookGenres().contains(newGenre));
    }
}
