package cz.upce.bookcase.service;

import cz.upce.bookcase.entity.Book;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<Book> findAll();
    Book findById(Integer id);
    void save(Book book);
    void deleteById(Integer id);
}
