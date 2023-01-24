package se.lexicon.springboot_workshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
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
    public void run(String... args) throws Exception {

        Details details = new Details("nive@gmail.com", "Nive", LocalDate.parse("1999-01-01"));


        AppUser appUser = new AppUser("Nive", "1234", LocalDate.now(), details);

        appUser.setDetails(details);  // Caused by: org.hibernate.PersistentObjectException: detached entity passed to persist: se.lexicon.springboot_workshop.entity.Details



        Details details1 = new Details("jay@gmail.com", "Jay", LocalDate.parse("1995-01-05"));

        AppUser appUser1 = new AppUser("Jay", "6789", LocalDate.now(), details1);


        Book book = new Book("As157", "Digital Principle and digital Design", 10);

        Book book1 = new Book("Asbn167", "Distributed Networks", 10);

        Book book2 = new Book("As237", "Mobile Computing", 10);


        // Populating book set

        Set<Book> bookList = Stream.of(book1, book).collect(Collectors.toCollection(HashSet::new));

        Set<Book> bookList1 = new HashSet<>();

        bookList1.add(book2);


        Author author = new Author("schilberschatz", "Anders");

        Author author1 = new Author("John", "Wakerly");


        //book.setAuthors(author);

       //book1.setAuthors(author);


       // book2.setAuthors(author1);

      //  author.setWrittenBook(bookList);
       // author1.setWrittenBook(bookList1);


        BookLoan bookLoan = new BookLoan(LocalDate.now(), LocalDate.parse("2023-02-01"), false);
        BookLoan bookLoan1 = new BookLoan(LocalDate.now(), LocalDate.parse("2023-02-01"), false);


        detailsDAO.create(details);
       detailsDAO.create(details1);

        appUserDAO.create(appUser);




       appUserDAO.create(appUser1);

       bookDAO.create(book);
       bookDAO.create(book1);
        bookDAO.create(book2);

        authorDAO.create(author);
        authorDAO.create(author1);

       bookLoanDAOimpl.create(bookLoan);
      //  bookLoanDAOimpl.create(bookLoan1);




    }
}
