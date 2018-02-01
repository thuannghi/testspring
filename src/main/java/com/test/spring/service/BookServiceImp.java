package com.test.spring.service;

import com.test.spring.controller.BookDto;
import com.test.spring.dao.BookDao;
import com.test.spring.exception.ParameterLowerThanZeroException;
import com.test.spring.model.Author;
import com.test.spring.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookServiceImp implements BookService {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private AuthorService authorService;

    @Override
    @Transactional()
    public long save(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setCategory(bookDto.getCategory());
        book.setDescription(bookDto.getDescription());
        Author author = authorService.findAuthorByName(bookDto.getAuthor());
        if (author == null) {
            author = new Author();
            author.setName(bookDto.getAuthor());
            author = authorService.saveAuthor(author);
        }
        book.setAuthor(author);
        return bookDao.save(book);

    }

    public Book get(long id) {
        return bookDao.get(id);
    }

    public List<Book> list() {
        return bookDao.list();
    }

    @Override
    @Transactional
    public List<Book> searchBook(String bookName, int size, int page) throws Exception {
        if ((size < 0) || (page < 0)) {
            throw new ParameterLowerThanZeroException("Size cann't lower than 0");
        } else {
            List<Book> allBooks = bookDao.getBook(bookName, size, page);
            return allBooks;
        }
    }

    @Transactional
    public void update(long id, Book book) {
        bookDao.update(id, book);
    }

    @Transactional
    public void delete(long id) {
        bookDao.delete(id);
    }

    @Override
    public Author getBookByAuthor(String searchAuthor) {
        Author author = bookDao.getAuthorByName(searchAuthor);
        return author;
    }
}