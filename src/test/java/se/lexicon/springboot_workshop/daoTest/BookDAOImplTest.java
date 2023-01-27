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

import javax.persistence.Query;
import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@AutoConfigureTestEntityManager
@DirtiesContext
public class BookDAOImplTest {

    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    AuthorDAOImpl authorDAOTest;
    @Autowired
    BookDAOImpl bookDAOImplTest;


    Book bookCreated;

    Author authorCreated;

    @BeforeEach
    public void setup() {
        Author author = new Author("schilberschatz", "Anders");
        testEntityManager.persist(author);


        Author author1 = new Author("John", "Wakerly");
        testEntityManager.persist(author1);



        Book book = new Book("As157", "Digital Principle and digital Design", 10);
        book.setAuthorsset(author);
        testEntityManager.persist(book);


        Book book1 = new Book("Asbn167", "Distributed Networks", 10);
        book1.setAuthorsset(author);
        testEntityManager.persist(book1);


        Book book2 = new Book("As237", "Mobile Computing", 10);
        book2.setAuthorsset(author1);
        testEntityManager.persist(book2);



    }

    @Test
    public void persist() {


        Author authortest = new Author("Rod", "Jhonson");
        authorCreated = authorDAOTest.create(authortest);

        Book booktest = new Book("As157", "Digital Principle and digital Design", 10);
        booktest.setAuthorsset(authortest);

        bookCreated = bookDAOImplTest.create(booktest);


        assertNotNull(authorCreated);

        assertNotNull((authorCreated.getAuthorId()));

        assertNotNull(bookCreated);
        assertNotNull(bookCreated.getBookId());


        // To check nullable column
        //   assertThrows(DataIntegrityViolationException.class,()->{ authorDAOTest.create(new Author());});


    }


    @Test
    public void findAll() {
        assertEquals(bookDAOImplTest.findAll().size(), 6);
        System.out.println(bookDAOImplTest.findAll());
    }


    @Test
    public void findById() {

        Author authortest = new Author("Rod", "Jhonson");
        authorCreated = authorDAOTest.create(authortest);

        Book booktest = new Book("As157", "Digital Principle and digital Design", 10);
        booktest.setAuthorsset(authortest);

        bookCreated = bookDAOImplTest.create(booktest);


        assertEquals(bookDAOImplTest.findById(bookCreated.getBookId()).getBookId(), 7);
    }

    @Test

    public void authorBookSave() {

        Author authortest = new Author("Rod", "Jhonson");
        authorCreated = authorDAOTest.create(authortest);



        Book booktest = new Book("As157", "Digital Principle and digital Design", 10);
        booktest.setAuthorsset(authortest);

        bookCreated = bookDAOImplTest.create(booktest);

        int id= bookCreated.getBookId();
        // reference https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/jpql-inner-join.html

        assertEquals(   testEntityManager.getEntityManager().
                createQuery("SELECT a  FROM Book a inner JOIN  a.authors b WHERE a.id = ?1")
                .setParameter(1,id)
                .getSingleResult(),bookCreated);



        // assertEquals(testEntityManager.getEntityManager().
        //   createQuery("SELECT a  FROM Author a inner JOIN  a.writtenBook b WHERE a.id = ?1")
        //    .setParameter(1,id)
        //    .getSingleResult(),null);

    }

    @Test
    public void merge() {


        Author authortest = new Author("Rod", "Jhonson");
        authorCreated = authorDAOTest.create(authortest);



        Book booktest = new Book("As157", "Digital Principle and digital Design", 10);
        booktest.setAuthorsset(authortest);

        bookCreated = bookDAOImplTest.create(booktest);

        bookCreated.setTitle("MergeTest");

        bookDAOImplTest.update(bookCreated);

        assertEquals(bookCreated.getTitle(),"MergeTest");
    }

  @Test
    public void delete() {

        Author authortest = new Author("Rod", "Jhonson");
        authorCreated = authorDAOTest.create(authortest);

        int id= authorCreated.getAuthorId();

        Book booktest = new Book("As157", "Digital Principle and digital Design", 10);
        booktest.setAuthorsset(authortest);

        bookCreated = bookDAOImplTest.create(booktest);



       bookDAOImplTest.delete(bookCreated.getBookId());

      assertThrows(javax.persistence.NoResultException.class,()->
      {testEntityManager.getEntityManager(). createQuery("SELECT a  FROM Book a inner JOIN  a.authors b WHERE a.id = ?1")
                        .setParameter(1,bookCreated.getBookId()).getSingleResult(); })  ;


    }


}
