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
import se.lexicon.springboot_workshop.DAO.impl.AppUserDAOImpl;
import se.lexicon.springboot_workshop.DAO.impl.AuthorDAOImpl;
import se.lexicon.springboot_workshop.DAO.impl.BookDAOImpl;
import se.lexicon.springboot_workshop.DAO.impl.DetailsDAOImpl;
import se.lexicon.springboot_workshop.entity.AppUser;
import se.lexicon.springboot_workshop.entity.Author;
import se.lexicon.springboot_workshop.entity.Book;
import se.lexicon.springboot_workshop.entity.Details;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@AutoConfigureTestEntityManager
@DirtiesContext
public class AuthorDAOImplTest {

    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    AuthorDAOImpl authorDAOTest;
    @Autowired
    BookDAOImpl bookDAOImplTest;


    Details details;

    AppUser appUser;

    @BeforeEach
    public void setup() {
        Author author = new Author("schilberschatz", "Anders");
        testEntityManager.persist(author);
        //authorDAOTest.create(author);

        Author author1 = new Author("John", "Wakerly");
        testEntityManager.persist(author1);
        //authorDAOTest.create(author1);


        Book book = new Book("As157", "Digital Principle and digital Design", 10);
        book.setAuthorsset(author);
        testEntityManager.persist(book);
        // bookDAOImplTest.create(book);


        Book book1 = new Book("Asbn167", "Distributed Networks", 10);
        book1.setAuthorsset(author);
        testEntityManager.persist(book1);
        // bookDAOImplTest.create(book1);

        Book book2 = new Book("As237", "Mobile Computing", 10);
        book2.setAuthorsset(author1);
        testEntityManager.persist(book2);

        //bookDAOImplTest.create(book2);

    }

    @Test
    public void persist() {


        Author authortest = new Author("Rod", "Jhonson");
        Author authorCreated = authorDAOTest.create(authortest);

        Book booktest = new Book("As157", "Digital Principle and digital Design", 10);
        booktest.setAuthorsset(authortest);

        Book bookCreated = bookDAOImplTest.create(booktest);


        assertNotNull(authorCreated);
        assertNotNull(authorCreated.getAuthorId());

        assertNotNull(booktest);
        assertNotNull(booktest.getBookId());

        // Since Mail is Unique key

 // To check nullable column
        assertThrows(DataIntegrityViolationException.class,()->{ authorDAOTest.create(new Author());});


    }

/*
    @Test
    public void findall() {
        assertEquals(appUserDAOTest.findAll().size(), 3);
    }

    @Test
    public void findById() {
        assertEquals(appUserDAOTest.findById(appUser.getAppUserId()).getAppUserId(), 3);
    }

    @Test

    public void delete() {

        appUserDAOTest.delete(3);
        assertEquals(appUserDAOTest.findAll().size(), 2);
    }*/
}
