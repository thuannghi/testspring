package com.test.spring.service;

import com.test.spring.dao.BookDao;
import com.test.spring.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthorService {
    @Autowired
    private BookDao bookDao;

    public Author findAuthorByName(String authorName) {
        return bookDao.getAuthorByName(authorName);
    }

    @Transactional
    public Author saveAuthor(Author author) {
        return bookDao.saveAuthor(author);
    }
}
