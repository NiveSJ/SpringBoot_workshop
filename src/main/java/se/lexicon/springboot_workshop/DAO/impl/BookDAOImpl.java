package se.lexicon.springboot_workshop.DAO.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.springboot_workshop.DAO.IBookDAO;
import se.lexicon.springboot_workshop.DAO.IBookLoanDAO;
import se.lexicon.springboot_workshop.entity.Book;
import se.lexicon.springboot_workshop.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
@Repository
public class BookDAOImpl implements IBookDAO {
    @PersistenceContext
    EntityManager entityManager;



    @Override
    @Transactional
    public Book findById(int id) {
        if(id == 0) throw new IllegalArgumentException(">> From Book DAO: Id Cannot be null");
        return entityManager.find(Book.class,id);
    }

    @Override
    @Transactional
    public Collection<Book> findAll() {
        return entityManager.createNamedQuery("findAll").getResultList();
    }

    @Override
    @Transactional
    public Book create(Book Book) {
        if (Book == null) throw new IllegalArgumentException("Book Cannot be null");

        entityManager.persist(Book);
        return Book;
    }

    @Override
    @Transactional
    public Book update(Book Book) {
        if (Book == null) throw new IllegalArgumentException("Book Cannot be null");

        return entityManager.merge(Book);

    }

    @Override
    @Transactional
    public void delete(int id) {
        Book Book= entityManager.find(Book.class,id);
        if (Book!= null) entityManager.remove(Book);
        else throw new IllegalArgumentException("no Item found to remove");

    }
}
