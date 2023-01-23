package se.lexicon.springboot_workshop.DAO;

import se.lexicon.springboot_workshop.DAO.impl.BookLoanDAO;
import se.lexicon.springboot_workshop.entity.Book;
import se.lexicon.springboot_workshop.entity.BookLoan;

import java.util.Collection;

public interface IBookLoanDAO {

    BookLoan findById(int id);

    Collection<BookLoan> findAll();

    BookLoan create();

    BookLoan update(BookLoan bookLoan);

    void delete(BookLoan bookLoan);
}
