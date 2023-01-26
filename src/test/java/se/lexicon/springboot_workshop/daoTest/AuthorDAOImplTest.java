package se.lexicon.springboot_workshop.daoTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import se.lexicon.springboot_workshop.DAO.impl.AppUserDAOImpl;
import se.lexicon.springboot_workshop.DAO.impl.DetailsDAOImpl;
import se.lexicon.springboot_workshop.entity.AppUser;
import se.lexicon.springboot_workshop.entity.Details;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorDAOImplTest {

    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    AppUserDAOImpl appUserDAOTest;
    @Autowired
    DetailsDAOImpl detailsDAOTest;


    Details details;

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
        AppUser appUser3 = new AppUser("Test1", "1234", LocalDate.now(), details2);

        AppUser createdAppUser2= appUserDAOTest.create(appUser2);
        Details createdDetails2=detailsDAOTest.create(details2);


        assertNotNull(createdAppUser2);
        assertNotNull(createdAppUser2.getAppUserId());

        assertNotNull(createdDetails2);
        assertNotNull(createdDetails2.getDetailsId());

        // Since Mail is Unique key
        assertThrows(DataIntegrityViolationException.class,()->{appUserDAOTest.create(appUser3);});
        assertThrows(DataIntegrityViolationException.class,()->{detailsDAOTest.create(details3);});
    }


    @Test
    public void findall() {
        assertEquals(appUserDAOTest.findAll().size(),3);
    }

    @Test
    public void findById() {
        assertEquals(appUserDAOTest.findById(appUser.getAppUserId()).getAppUserId(),3);
    }

    @Test

    public void delete() {

        appUserDAOTest.delete(3);
        assertEquals(appUserDAOTest.findAll().size(),2);
    }
}
