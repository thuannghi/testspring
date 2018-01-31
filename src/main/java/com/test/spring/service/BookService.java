package com.test.spring.service;

import com.test.spring.controller.BookDto;
import com.test.spring.model.Author;
import com.test.spring.model.Book;

import java.util.List;

public interface BookService {

    long save(BookDto bookDto);

    Book get(long id);

    List<Book> list();

    List<Book> searchBook(String bookName,int size, int page);

    void update(long id, Book book);

    void delete(long id);

    Author getBookByAuthor(String searchAuthor);

}
