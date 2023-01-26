package se.lexicon.springboot_workshop.daoTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.springboot_workshop.DAO.impl.AppUserDAOImpl;
import se.lexicon.springboot_workshop.DAO.impl.DetailsDAOImpl;
import se.lexicon.springboot_workshop.entity.AppUser;
import se.lexicon.springboot_workshop.entity.Details;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
@Transactional
@AutoConfigureTestEntityManager
@DirtiesContext
public class DetailsDAOImplTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    DetailsDAOImpl detailsDAOTest;


    Details details,details2,details3;

    AppUser appUser;
    @BeforeEach
    public void setup() {
        details = new Details("nive1@gmail.com", "Nive1", LocalDate.parse("1999-01-01"));
        Details createdDetails = testEntityManager.persist(details);


    }

    @Test
    public void persist() {

        Details details2 = new Details("Test1@gmail.com", "Test1", LocalDate.parse("1999-01-01"));
        Details createdDetails2=detailsDAOTest.create(details2);

        assertNotNull(createdDetails2);
        assertNotNull(createdDetails2.getDetailsId());


    }


    @Test
    public void findAll() {

        Details details2 = new Details("Test2@gmail.com", "Test2", LocalDate.parse("1999-01-01"));
        assertEquals(detailsDAOTest.findAll().size(),3);

        Details createdDetails2=detailsDAOTest.create(details2);
        assertEquals(detailsDAOTest.findAll().size(),4);
    }

    @Test
    public void findById() {

        Details details2 = new Details("Test3@gmail.com", "Test3", LocalDate.parse("1999-01-01"));
        AppUser appUser2 = new AppUser("Test3", "1234", LocalDate.now(), details2);



        Details createdDetails2=detailsDAOTest.create(details2);
        assertEquals(detailsDAOTest.findById(createdDetails2.getDetailsId()).getDetailsId(),4);
    }

    @Test

    public void delete() {

        Details details2 = new Details("Test4@gmail.com", "Test4", LocalDate.parse("1999-01-01"));


        Details createdDetails2=detailsDAOTest.create(details2);
        assertEquals(detailsDAOTest.findAll().size(),4);

        detailsDAOTest.delete(createdDetails2.getDetailsId());
        assertEquals(detailsDAOTest.findAll().size(),3);
    }

}
