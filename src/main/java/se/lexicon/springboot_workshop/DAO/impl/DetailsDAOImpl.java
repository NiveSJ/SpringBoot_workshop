package se.lexicon.springboot_workshop.DAO.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.springboot_workshop.DAO.IDetailsDAO;
import se.lexicon.springboot_workshop.entity.Details;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
@Repository
public class DetailsDAOImpl implements IDetailsDAO {
    @PersistenceContext
    EntityManager entityManager;



    @Override
    @Transactional (readOnly = true)
    public Details findById(int id) {
        if(id == 0) throw new IllegalArgumentException(">> From Details DAO: Id Cannot be null");
        return entityManager.find(Details.class,id);
    }

    @Override
    @Transactional (readOnly = true)
    public Collection<Details> findAll() {
        return entityManager.createNamedQuery("Details.findAll").getResultList();
    }

    @Override
    @Transactional
    public Details create(Details details) {
        if (details == null) throw new IllegalArgumentException("Details Cannot be null");

        entityManager.persist(details);
        return details;
    }

    @Override
    @Transactional
    public Details update(Details details) {
        if (details == null) throw new IllegalArgumentException("Details Cannot be null");

        return entityManager.merge(details);

    }

    @Override
    @Transactional
    public void delete(int id) {
        Details details= entityManager.find(Details.class,id);
        if (details!= null) entityManager.remove(details);
        else throw new IllegalArgumentException("no Item found to remove");

    }
}
