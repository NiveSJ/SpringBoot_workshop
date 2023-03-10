package se.lexicon.springboot_workshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.springboot_workshop.DAO.impl.*;
import se.lexicon.springboot_workshop.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AppCommandLineRunner implements CommandLineRunner {


    AppUserDAOImpl appUserDAO;
    AuthorDAOImpl authorDAO;
    BookDAOImpl bookDAO;
    BookLoanDAOimpl bookLoanDAOimpl;
    DetailsDAOImpl detailsDAO;

    @Autowired
    public AppCommandLineRunner(AppUserDAOImpl appUserDAO, AuthorDAOImpl authorDAO, BookDAOImpl bookDAO, BookLoanDAOimpl bookLoanDAOimpl, DetailsDAOImpl detailsDAO) {
        this.appUserDAO = appUserDAO;
        this.authorDAO = authorDAO;
        this.bookDAO = bookDAO;
        this.bookLoanDAOimpl = bookLoanDAOimpl;
        this.detailsDAO = detailsDAO;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Details details = new Details("nive@gmail.com", "Nive", LocalDate.parse("1999-01-01"));
        AppUser appUser = new AppUser("Nive", "1234", LocalDate.now(), details);
        appUser.setDetails(details);

        // Caused by: org.hibernate.PersistentObjectException:
        // detached entity passed to persist: se.lexicon.springboot_workshop.entity.Details


        Details details1 = new Details("jay@gmail.com", "Jay", LocalDate.parse("1995-01-05"));
        AppUser appUser1 = new AppUser("Jay", "6789", LocalDate.now(), details1);
        appUser1.setDetails(details1);


        detailsDAO.create(details);
        detailsDAO.create(details1);

        appUserDAO.create(appUser);
        appUserDAO.create(appUser1);



        Author author = new Author("schilberschatz", "Anders");
        authorDAO.create(author);

        Author author1 = new Author("John", "Wakerly");
        authorDAO.create(author1);



        Book book = new Book("As157", "Digital Principle and digital Design", 10);
        book.setAuthorsset(author);
        bookDAO.create(book);



        Book book1 = new Book("Asbn167", "Distributed Networks", 10);
        book1.setAuthorsset(author);
        bookDAO.create(book1);

        Book book2 = new Book("As237", "Mobile Computing", 10);
        book2.setAuthorsset(author1);
        bookDAO.create(book2);




      // creating Book Loan Entry in DB

        BookLoan bookLoan = new BookLoan(LocalDate.now(), LocalDate.parse("2023-02-01"), false);
        bookLoan.setBorrower(appUser);
        bookLoan.setBook(book);

        BookLoan bookLoan1 = new BookLoan(LocalDate.now(), LocalDate.parse("2023-02-01"), false);
        bookLoan1.setBorrower(appUser1);
        bookLoan1.setBook(book2);


        bookLoanDAOimpl.create(bookLoan);
        bookLoanDAOimpl.create(bookLoan1);











    }
}
