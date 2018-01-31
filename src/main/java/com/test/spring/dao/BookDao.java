package com.test.spring.dao;

import com.test.spring.model.Author;
import com.test.spring.model.Book;

import java.util.List;

public interface BookDao {

    long save(Book book);

    Book get(long id);

    List<Book> list();

    void update(long id, Book book);

    void delete(long id);

    Author getAuthorByName(String authorName);

    Author saveAuthor(Author author);

    List<Book> getBook(String bookName, int size, int page);

}
