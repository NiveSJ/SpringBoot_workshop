package se.lexicon.springboot_workshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.springboot_workshop.DAO.impl.*;
import se.lexicon.springboot_workshop.entity.*;

import java.time.LocalDate;
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




      // Crerating Book Loan Entry in DB

        BookLoan bookLoan = new BookLoan(LocalDate.now(), LocalDate.parse("2023-02-01"), false);
        bookLoan.setBorrower(appUser);
        bookLoan.setBook(book);

        BookLoan bookLoan1 = new BookLoan(LocalDate.now(), LocalDate.parse("2023-02-01"), false);
        bookLoan1.setBorrower(appUser1);
        bookLoan1.setBook(book1);


        bookLoanDAOimpl.create(bookLoan);
        bookLoanDAOimpl.create(bookLoan1);

        //Find All Execution

        System.out.println( ">>Details of all appUser  :" +appUserDAO.findAll());



        System.out.println( ">>Details of all Details Entity   :" +detailsDAO.findAll());


        System.out.println( ">>Details of all BookLoan  :" +bookLoanDAOimpl.findAll());


        System.out.println( ">>Details of all Author  :" );
       authorDAO.findAll().forEach(author2 -> {System.out.println(author2);
           System.out.println(author2.getWrittenBook());});


       System.out.println( ">>Details of all Book  :");
      bookDAO.findAll().forEach(book3 -> {System.out.println(book3);
           System.out.println(book3.getAuthors());
      });


       // Find By Id With Entities

        System.out.println("\n>>>>>>>>>>>> findby Id App User "+ appUserDAO.findById(appUser1.getAppUserId()));

        System.out.println("\n>>>>>>>>>>>> findby Id App User "+ appUserDAO.findById(appUser.getAppUserId()));

        System.out.println("\n>>>>>>>>>>>> findby Id App User "+ authorDAO.findById(author.getAuthorId()));

        System.out.println("\n>>>>>>>>>>>> findby Id App User "+ authorDAO.findById(author1.getAuthorId()));

        System.out.println("\n>>>>>>>>>>>> findby Id App User "+ bookDAO.findById(book.getBookId()));

        System.out.println("\n>>>>>>>>>>>> findby Id App User "+ bookDAO.findById(book1.getBookId()));

        System.out.println("\n>>>>>>>>>>>> findby Id App User "+ bookLoanDAOimpl.findById(bookLoan.getLoanId()));

        System.out.println("\n>>>>>>>>>>>> findby Id App User "+ bookLoanDAOimpl.findById(bookLoan1.getLoanId()));


        System.out.println("\n>>>>>>>>>>>> findby Id App User "+ detailsDAO.findById(details1.getDetailsId()));

        System.out.println("\n>>>>>>>>>>>> findby Id App User "+ detailsDAO.findById(details.getDetailsId()));

        // Update Entities

        appUser.setUserName("Nivethitha");
        System.out.println("\n>>>>>>>>>>>> Update App User "+ appUserDAO.update(appUser));

        author1.setFirstName("Jack");

        System.out.println("\n>>>>>>>>>>>> Update Author "+ authorDAO.update(author1));

         book.setTitle("Data Structures");
        System.out.println("\n>>>>>>>>>>>> Update Book "+ bookDAO.update(book));

        bookLoan.setLoanDate(LocalDate.parse("2023-01-20"));
        bookLoan.setBorrower(appUser1);
        bookLoan.setBook(book2);
        System.out.println("\n>>>>>>>>>>>> Update BookLoan "+ bookLoanDAOimpl.update(bookLoan));

        details1.setEmail("Test@gmail.com");

        System.out.println("\n>>>>>>>>>>>> Update Details "+ detailsDAO.update(details1));



        // Executing Remove

        System.out.println("\n>>>>>>>>>>>>Performing delete operations ");

       //appUserDAO.delete(appUser1.getAppUserId());

        //authorDAO.delete(author1.getAuthorId());
      //  bookLoanDAOimpl.delete(bookLoan1.getLoanId());

        //book.removeAuthorsset(author);

        bookDAO.delete(book.getBookId());














    }
}
