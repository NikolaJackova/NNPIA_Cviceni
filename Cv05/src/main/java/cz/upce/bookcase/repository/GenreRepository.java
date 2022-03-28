package cz.upce.bookcase.repository;

import cz.upce.bookcase.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Optional<Genre> findByName(String name);
    void deleteByName(String name);
}
