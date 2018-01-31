package com.test.spring.dao;

import com.test.spring.model.Author;
import com.test.spring.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class BookDaoIml implements BookDao {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public long save(Book book) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.persist(book);
        em.close();
        return book.getId();
    }

    @Override
    public Book get(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Book book = em.find(Book.class, id);
        em.close();
        return book;
    }

    @Override
    public List<Book> list() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Book> books = em.createQuery("from Book").getResultList();
        em.close();
        return books;
    }

    @Override
    public void update(long id, Book book) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Book originalBook = em.find(Book.class, id);
        originalBook.setTitle(book.getTitle());
        originalBook.setAuthor(book.getAuthor());
        em.close();
    }
    @Override
    public Author getAuthorByName(String authorName) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Query query = em.createQuery("from Author b where b.name like :name");
        query.setParameter("name", "%" + authorName + "%");
        List<Author> authors = query.getResultList();
        if (authors.isEmpty()) {
            return null;
        }
        em.close();
        return authors.get(0);
    }

    public List<Book> getBook(String bookname, int size, int page) {

        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("from Book b where b.title like :name");
        query.setParameter("name", "%" + bookname + "%");
        List<Book> books = query.getResultList();
        page = page - 1;
        int setpage = size*page;
        query.setFirstResult(setpage);
        query.setMaxResults(size);
        books = query.getResultList();
        em.close();
        return books;
    }

    @Override
    public void delete(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Book book = em.find(Book.class, id);
        em.remove(book);
    }

    public Author saveAuthor(Author author) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.persist(author);
        em.close();
        return author;
    }
}