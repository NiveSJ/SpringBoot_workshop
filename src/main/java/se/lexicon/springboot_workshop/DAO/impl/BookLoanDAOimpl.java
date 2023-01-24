package se.lexicon.springboot_workshop.DAO.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.springboot_workshop.DAO.IBookLoanDAO;

import se.lexicon.springboot_workshop.entity.BookLoan;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
@Repository
public class BookLoanDAOimpl implements IBookLoanDAO {
    @PersistenceContext
    EntityManager entityManager;



    @Override
    @Transactional
    public BookLoan findById(int id) {
        if(id == 0) throw new IllegalArgumentException(">> From BookLoan DAO: Id Cannot be null");
        return entityManager.find(BookLoan.class,id);
    }

    @Override
    @Transactional
    public Collection<BookLoan> findAll() {
        return entityManager.createNamedQuery("findAll").getResultList();
    }

    @Override
    @Transactional
    public BookLoan create(BookLoan bookLoan) {
        if (bookLoan == null) throw new IllegalArgumentException("BookLoan Cannot be null");

        entityManager.persist(bookLoan);
        return bookLoan;
    }

    @Override
    @Transactional
    public BookLoan update(BookLoan BookLoan) {
        if (BookLoan == null) throw new IllegalArgumentException("BookLoan Cannot be null");

        return entityManager.merge(BookLoan);

    }

    @Override
    @Transactional
    public void delete(int id) {
        BookLoan BookLoan= entityManager.find(BookLoan.class,id);
        if (BookLoan!= null) entityManager.remove(BookLoan);
        else throw new IllegalArgumentException("no Item found to remove");

    }
}
