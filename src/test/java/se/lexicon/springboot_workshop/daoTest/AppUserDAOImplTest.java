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
import se.lexicon.springboot_workshop.DAO.impl.DetailsDAOImpl;
import se.lexicon.springboot_workshop.entity.AppUser;
import se.lexicon.springboot_workshop.entity.Author;
import se.lexicon.springboot_workshop.entity.Details;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@AutoConfigureTestEntityManager
@DirtiesContext
public class AppUserDAOImplTest {

    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    AppUserDAOImpl appUserDAOTest;
    @Autowired
    DetailsDAOImpl detailsDAOTest;


    Details details,details2,details3;

    AppUser appUser;
    @BeforeEach
    public void setup() {
         details = new Details("nive1@gmail.com", "Nive1", LocalDate.parse("1999-01-01"));
         appUser = new AppUser("Nive1", "1234", LocalDate.now(), details);
        appUser.setDetails(details);

        Details createdDetails = testEntityManager.persist(details);
        AppUser createdAppUser = testEntityManager.persist(appUser);

    }

    @Test
    public void persist() {

        Details details2 = new Details("Test1@gmail.com", "Test1", LocalDate.parse("1999-01-01"));
        AppUser appUser2 = new AppUser("Test1", "1234", LocalDate.now(), details2);


        Details details3 = new Details("Test1@gmail.com", "Test1", LocalDate.parse("1999-01-01"));
        AppUser appUser3 = new AppUser("Test1", "1234", LocalDate.now(), details3);

        AppUser createdAppUser2= appUserDAOTest.create(appUser2);
        Details createdDetails2=detailsDAOTest.create(details2);


        assertNotNull(createdAppUser2);
        assertNotNull(createdAppUser2.getAppUserId());

        assertNotNull(createdDetails2);
        assertNotNull(createdDetails2.getDetailsId());

         // Since Mail is Unique key
        assertThrows(DataIntegrityViolationException.class,()->{appUserDAOTest.create(appUser3);});


        // to Check Not null fields
        assertThrows(DataIntegrityViolationException.class,()->{ appUserDAOTest.create(new AppUser());});

    }


@Test
    public void findAll() {

    Details details2 = new Details("Test2@gmail.com", "Test2", LocalDate.parse("1999-01-01"));
    AppUser appUser2 = new AppUser("Test2", "1234", LocalDate.now(), details2);


    AppUser createdAppUser2= appUserDAOTest.create(appUser2);
    Details createdDetails2=detailsDAOTest.create(details2);


       assertEquals(appUserDAOTest.findAll().size(),4);
    }

@Test
    public void findById() {

    Details details2 = new Details("Test3@gmail.com", "Test3", LocalDate.parse("1999-01-01"));
    AppUser appUser2 = new AppUser("Test3", "1234", LocalDate.now(), details2);


    AppUser createdAppUser2= appUserDAOTest.create(appUser2);
    Details createdDetails2=detailsDAOTest.create(details2);
        assertEquals(appUserDAOTest.findById(createdAppUser2.getAppUserId()).getAppUserId(),4);
    }
  @Test
    public void merge() {

        Details details2 = new Details("Test4@gmail.com", "Test4", LocalDate.parse("1999-01-01"));
        AppUser appUser2 = new AppUser("Test4", "1234", LocalDate.now(), details2);


        AppUser createdAppUser2= appUserDAOTest.create(appUser2);

        createdAppUser2.setUserName("Test5");
      System.out.println(createdAppUser2);
        appUserDAOTest.update(createdAppUser2);

        assertEquals(createdAppUser2.getUserName(),"Test5");
    }

    @Test

    public void delete() {

        Details details2 = new Details("Test4@gmail.com", "Test4", LocalDate.parse("1999-01-01"));
        AppUser appUser2 = new AppUser("Test4", "1234", LocalDate.now(), details2);


        AppUser createdAppUser2= appUserDAOTest.create(appUser2);
        appUserDAOTest.delete(3);
        assertEquals(appUserDAOTest.findAll().size(),3);
    }


}
