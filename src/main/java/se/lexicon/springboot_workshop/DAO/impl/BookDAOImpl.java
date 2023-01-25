package se.lexicon.springboot_workshop.DAO.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.springboot_workshop.DAO.IBookDAO;

import se.lexicon.springboot_workshop.entity.Book;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
@Repository
public class BookDAOImpl implements IBookDAO {
    @PersistenceContext
    EntityManager entityManager;



    @Override
    @Transactional (readOnly = true)
    public Book findById(int id) {
        if(id == 0) throw new IllegalArgumentException(">> From Book DAO: Id Cannot be null");
        return entityManager.find(Book.class,id);
    }

    @Override
    @Transactional (readOnly = true)
    public Collection<Book> findAll() {
        return entityManager.createNamedQuery("Book.findAll").getResultList();
    }

    @Override
    @Transactional
    public Book create(Book book) {
        if (book == null) throw new IllegalArgumentException("Book Cannot be null");
        entityManager.persist(book);
        return book;
    }

    @Override
    @Transactional
    public Book update(Book book) {
        if (book == null) throw new IllegalArgumentException("Book Cannot be null");

        return entityManager.merge(book);

    }

    @Override
    @Transactional
    public void delete(int id) {
        Book book= entityManager.find(Book.class,id);
        if (book!= null) entityManager.remove(book);
        else throw new IllegalArgumentException("no Item found to remove");

    }
}
