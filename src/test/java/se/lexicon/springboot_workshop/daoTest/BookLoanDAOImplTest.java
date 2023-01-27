package se.lexicon.springboot_workshop.daoTest;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.springboot_workshop.DAO.impl.*;
import se.lexicon.springboot_workshop.entity.*;

import javax.persistence.Query;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@AutoConfigureTestEntityManager
@DirtiesContext
public class BookLoanDAOImplTest {

    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    AuthorDAOImpl authorDAOTest;
    @Autowired
    BookDAOImpl bookDAOImplTest;

    @Autowired
    BookLoanDAOimpl bookLoanDAOimpl;

    Details details;
    AppUser appUser;
    Book bookCreated;

    Author authorCreated;

    BookLoan bookLoan;

    Book book;

    @BeforeEach
    public void setup() {

        details = new Details("nive1@gmail.com", "Nive1", LocalDate.parse("1999-01-01"));
        appUser = new AppUser("Nive1", "1234", LocalDate.now(), details);
        appUser.setDetails(details);

        Details createdDetails = testEntityManager.persist(details);
        AppUser createdAppUser = testEntityManager.persist(appUser);

        Author author = new Author("schilberschatz", "Anders");
        testEntityManager.persist(author);


        Book book = new Book("As157", "Digital Principle and digital Design", 10);
        book.setAuthorsset(author);
        testEntityManager.persist(book);


        bookLoan = new BookLoan(LocalDate.now(), LocalDate.parse("2023-02-01"), false);
        bookLoan.setBorrower(appUser);
        bookLoan.setBook(book);

        testEntityManager.persist(bookLoan);


    }

    @Test
    public void persist() {


        bookLoan = new BookLoan(LocalDate.now(), LocalDate.parse("2023-02-01"), false);
        bookLoan.setBorrower(appUser);
        bookLoan.setBook(book);


        BookLoan CreatedBookLoan = bookLoanDAOimpl.create(bookLoan);


        assertNotNull(CreatedBookLoan);
        assertNotNull(CreatedBookLoan.getLoanId());



        // To check nullable column
        //   assertThrows(DataIntegrityViolationException.class,()->{ authorDAOTest.create(new Author());});


    }


    @Test
    public void findAll() {
        assertEquals(bookLoanDAOimpl.findAll().size(), 3);
        System.out.println(bookLoanDAOimpl.findAll());
    }


    @Test
    public void findById() {

        bookLoan = new BookLoan(LocalDate.now(), LocalDate.parse("2023-02-01"), false);
        bookLoan.setBorrower(appUser);
        bookLoan.setBook(book);

        BookLoan CreatedBookLoan = bookLoanDAOimpl.create(bookLoan);

        assertEquals(bookLoanDAOimpl.findById(CreatedBookLoan.getLoanId()).getLoanId(), 4);
    }

    @Test
    public void merge() {


        bookLoan = new BookLoan(LocalDate.now(), LocalDate.parse("2023-02-01"), false);
        bookLoan.setBorrower(appUser);
        bookLoan.setBook(book);

        Book book1 = new Book("As157", "Computer Networks", 10);

        BookLoan CreatedBookLoan = bookLoanDAOimpl.create(bookLoan);

        CreatedBookLoan.setBook(book1);

        bookLoanDAOimpl.update(CreatedBookLoan);

        assertEquals(CreatedBookLoan.getBook(),book1);
    }




    @Test
    public void delete() {

        bookLoan = new BookLoan(LocalDate.now(), LocalDate.parse("2023-02-01"), false);
        bookLoan.setBorrower(appUser);
        bookLoan.setBook(book);

        BookLoan CreatedBookLoan = bookLoanDAOimpl.create(bookLoan);

        assertNotNull(CreatedBookLoan);
        assertEquals(bookLoanDAOimpl.findAll().size(),4);
        bookLoanDAOimpl.delete(CreatedBookLoan.getLoanId());

        assertEquals(bookLoanDAOimpl.findAll().size(),3);


    }


}
