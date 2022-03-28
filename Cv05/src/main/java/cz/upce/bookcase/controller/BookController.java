package cz.upce.bookcase.controller;

import cz.upce.bookcase.dto.BookDTO;
import cz.upce.bookcase.entity.Book;
import cz.upce.bookcase.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {
    @ExceptionHandler(RuntimeException.class)
    public String handlerException(){ return "error"; }

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String showAllBooks(Model model){
        model.addAttribute("bookList", bookService.findAll());
        return "book-list";
    }

    @GetMapping("/book-detail/{id}")
    public String showBookDetail(@PathVariable Integer id, Model model){
        Book byId = bookService.findById(id);
        model.addAttribute("book", byId);
        return "book-detail";
    }

    @GetMapping(value = {"/book-form", "/book-form/{id}"})
    public String showBookForm(@PathVariable(required = false) Integer id, Model model) {
        if (id != null) {
            Book byId = bookService.findById(id);
            BookDTO bookDTO = new BookDTO();
            bookDTO.setBookId(byId.getBookId());
            bookDTO.setName(byId.getName());
            bookDTO.setDescription(byId.getDescription());
            bookDTO.setIsbn(byId.getIsbn());
            bookDTO.setPublishYear(byId.getPublishYear());
            bookDTO.setBookAuthors(byId.getBookAuthors());
            bookDTO.setBookGenres(byId.getBookGenres());

            model.addAttribute("book", bookDTO);
        } else {
            model.addAttribute("book", new BookDTO());
        }
        return "book-form";
    }
    @PostMapping("/book-form-process")
    public String bookFormProcess(BookDTO bookDTO){
        Book book = new Book();
        book.setBookId(bookDTO.getBookId());
        book.setDescription(bookDTO.getDescription());
        book.setIsbn(bookDTO.getIsbn());
        book.setName(bookDTO.getName());
        book.setPublishYear(bookDTO.getPublishYear());
        book.setBookGenres(bookDTO.getBookGenres());
        book.setBookAuthors(bookDTO.getBookAuthors());

        bookService.save(book);
        return "redirect:/";
    }
    @PostMapping("/book-remove-process")
    public String removeBook(Book book){
        bookService.deleteById(book.getBookId());
        return "redirect:/";
    }
}
