package cz.upce.bookcase.repository;

import cz.upce.bookcase.entity.BookAuthor;
import cz.upce.bookcase.entity.BookAuthorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorBookRepository extends JpaRepository<BookAuthor, BookAuthorId> {
}
