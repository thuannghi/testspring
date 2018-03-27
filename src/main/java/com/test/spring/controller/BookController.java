package com.test.spring.controller;

import com.test.spring.model.Author;
import com.test.spring.model.Book;
import com.test.spring.service.AuthorService;
import com.test.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;


    /*---Add new book---*/
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody BookDto bookDto) {

        long id = bookService.save(bookDto);
        return ResponseEntity.ok().body("New Book has been saved with ID:" + id);
    }

    /*---Get a book by id---*/
    @GetMapping("/book/{id}")
    public Book get(@PathVariable("id") long id) {
//        BookReponse bookReponse = bookService.get(id);
//        return bookReponse;
        return bookService.get(id);
    }

    /*---get all books---*/
    @RequestMapping(value = "/book", method = GET)
    public ResponseEntity<List<Book>> list() {
        List<Book> books = bookService.list();
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    /*---Update a book by id---*/
    @PutMapping("/book/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Book book) {
        bookService.update(id, book);
        return ResponseEntity.ok().body("Book has been updated successfully.");
    }

    /*---Delete a book by id---*/
    @DeleteMapping("/book/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        bookService.delete(id);
        return ResponseEntity.ok().body("Book has been deleted successfully.");
    }

    @RequestMapping(
            value = "/searchbook",
//            params = {"bookname", "size", "page"},
            method = GET)
    public ResponseEntity<List<Book>> searchBook(@RequestParam("bookname") String bookname, @RequestParam("size") int size, @RequestParam("page") int page) {
        List<Book> books = null;
        try {
            books = bookService.searchBook(bookname, size, page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(books);
    }

    @RequestMapping(
            value = "/searchauthor",
//            params = {"bookname", "size", "page"},
            method = GET)
    public ResponseEntity<List<Book>> getBookByAuthor(@RequestParam("author") String authorName) {
        Author author = bookService.getBookByAuthor(authorName);
        List<Book> books = author.getBooks();
        return ResponseEntity.ok().body(books);
    }

}