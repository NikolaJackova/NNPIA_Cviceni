package cz.upce.bookcase.dto;

import cz.upce.bookcase.entity.BookAuthor;
import cz.upce.bookcase.entity.Genre;
import lombok.Data;

import java.util.Set;

@Data
public class BookDTO {
    private Integer bookId;
    private String name;
    private String description;
    private String isbn;
    private Integer publishYear;
    private Set<BookAuthor> bookAuthors;
    private Set<Genre> bookGenres;
}
