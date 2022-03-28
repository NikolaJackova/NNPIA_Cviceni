package cz.upce.bookcase.repository;

import cz.upce.bookcase.entity.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @EntityGraph(attributePaths = {"bookGenres", "bookAuthors"})
    Optional<Book> findByName(String name);
    void deleteBookByName(String name);
    Optional<Book> findByBookGenres(Integer genreId);

    @EntityGraph(attributePaths = {"bookGenres", "bookAuthors"})
    Optional<Book> findById(Integer id);
}
