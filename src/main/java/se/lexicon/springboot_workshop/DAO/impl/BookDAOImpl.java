package se.lexicon.springboot_workshop.DAO.impl;

import se.lexicon.springboot_workshop.DAO.IBookDAO;
import se.lexicon.springboot_workshop.DAO.IBookLoanDAO;
import se.lexicon.springboot_workshop.entity.Book;

import java.util.Collection;

public class BookDAOImpl implements IBookDAO {
    @Override
    public Book findById(int id) {
        return null;
    }

    @Override
    public Collection<Book> findAll() {
        return null;
    }

    @Override
    public Book create() {
        return null;
    }

    @Override
    public Book update(Book book) {
        return null;
    }

    @Override
    public void delete(Book book) {

    }
}
