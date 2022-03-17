package cz.upce.bookcase.repository;

import cz.upce.bookcase.entity.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @EntityGraph(attributePaths = {"bookGenres"})
    Optional<Book> findByName(String name);
    void deleteBookByName(String name);
    Optional<Book> findByBookGenres(Integer genreId);
}
