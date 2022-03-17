package cz.upce.bookcase;

import cz.upce.bookcase.entity.Book;
import cz.upce.bookcase.entity.Genre;
import cz.upce.bookcase.repository.GenreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GenreTests {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void createGenreTest() {
        Genre newGenre = new Genre();
        newGenre.setName("Testovací žánr");

        genreRepository.save(newGenre);
        Assertions.assertEquals(newGenre, genreRepository.findByName("Testovací žánr".toUpperCase()).get());
    }

    @Test
    void updateGenreTest(){
        Genre newGenre = new Genre();
        newGenre.setName("Testovací žánr");

        genreRepository.save(newGenre);

        newGenre.setName("Upravený testovací žánr");
        genreRepository.save(newGenre);

        Assertions.assertEquals("Upravený testovací žánr", genreRepository.findByName("Upravený testovací žánr".toUpperCase()).get().getName());
    }

    @Test
    void deleteGenreTest(){
        Genre newGenre = new Genre();
        newGenre.setName("Testovací žánr");

        genreRepository.save(newGenre);
        genreRepository.deleteByName("Testovací žánr".toUpperCase());
        Assertions.assertEquals(true, genreRepository.findByName("Testovací žánr".toUpperCase()).isEmpty());
    }
}
