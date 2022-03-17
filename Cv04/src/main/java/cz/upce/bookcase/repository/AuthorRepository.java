package cz.upce.bookcase.repository;

import cz.upce.bookcase.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
