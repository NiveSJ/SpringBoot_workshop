package se.lexicon.springboot_workshop.DAO.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.springboot_workshop.DAO.IDetailsDAO;
import se.lexicon.springboot_workshop.entity.Details;
import se.lexicon.springboot_workshop.entity.Details;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
@Repository
public class DetailsDAOImpl implements IDetailsDAO {
    @PersistenceContext
    EntityManager entityManager;



    @Override
    @Transactional
    public Details findById(int id) {
        if(id == 0) throw new IllegalArgumentException(">> From Details DAO: Id Cannot be null");
        return entityManager.find(Details.class,id);
    }

    @Override
    @Transactional
    public Collection<Details> findAll() {
        return entityManager.createNamedQuery("findAll").getResultList();
    }

    @Override
    @Transactional
    public Details create(Details Details) {
        if (Details == null) throw new IllegalArgumentException("Details Cannot be null");

        entityManager.persist(Details);
        return Details;
    }

    @Override
    @Transactional
    public Details update(Details Details) {
        if (Details == null) throw new IllegalArgumentException("Details Cannot be null");

        return entityManager.merge(Details);

    }

    @Override
    @Transactional
    public void delete(int id) {
        Details Details= entityManager.find(Details.class,id);
        if (Details!= null) entityManager.remove(Details);
        else throw new IllegalArgumentException("no Item found to remove");

    }
}
