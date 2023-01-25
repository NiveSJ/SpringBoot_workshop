package se.lexicon.springboot_workshop.daoTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.springboot_workshop.DAO.impl.AppUserDAOImpl;
import se.lexicon.springboot_workshop.DAO.impl.DetailsDAOImpl;
import se.lexicon.springboot_workshop.entity.AppUser;
import se.lexicon.springboot_workshop.entity.Details;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @BeforeEach
    public void setup() {
        Details details = new Details("nive@gmail.com", "Nive", LocalDate.parse("1999-01-01"));
        AppUser appUser = new AppUser("Nive", "1234", LocalDate.now(), details);
        appUser.setDetails(details);

        // Caused by: org.hibernate.PersistentObjectException:
        // detached entity passed to persist: se.lexicon.springboot_workshop.entity.Details


        Details details1 = new Details("jay@gmail.com", "Jay", LocalDate.parse("1995-01-05"));
        AppUser appUser1 = new AppUser("Jay", "6789", LocalDate.now(), details1);
        appUser1.setDetails(details1);


        Details createdDetails = testEntityManager.persist(details);
        Details createdDetails1 = testEntityManager.persist(details1);

        AppUser createdAppUser = testEntityManager.persist(appUser);
        AppUser createdAppUser1  = testEntityManager.persist(appUser1);



    }

    @Test
    public void persist() {

        appUserDAO.create(appUser);
        appUserDAO.create(appUser1);

        Student studentData = new Student("Peter", "Peter", "peter@test.test", LocalDate.parse("2000-01-02"));
        Student createdStudent = testObject.persist(studentData);

        assertNotNull(createdStudent);
        assertNotNull(createdStudent.getId());


    }
}
