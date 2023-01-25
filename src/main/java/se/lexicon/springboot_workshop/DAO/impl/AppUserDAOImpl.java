package se.lexicon.springboot_workshop.DAO.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.springboot_workshop.DAO.IAppUserDAO;
import se.lexicon.springboot_workshop.entity.AppUser;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
@Repository
public class AppUserDAOImpl implements IAppUserDAO {
    @PersistenceContext
    EntityManager entityManager;



    @Override
    @Transactional (readOnly = true)
    public AppUser findById(int id) {
        if(id == 0) throw new IllegalArgumentException(">> From AppUser DAO: Id Cannot be null");
        return entityManager.find(AppUser.class,id);
    }

    @Override
    @Transactional (readOnly = true)
    public Collection<AppUser> findAll() {
        return entityManager.createNamedQuery("AppUser.findAll").getResultList();
    }

    @Override
    @Transactional
    public AppUser create(AppUser appUser) {
        if (appUser == null) throw new IllegalArgumentException("AppUser Cannot be null");
        entityManager.persist(appUser);
        return appUser;
    }

    @Override
    @Transactional
    public AppUser update(AppUser appUser) {

        if (appUser == null) throw new IllegalArgumentException("AppUser Cannot be null");
        return entityManager.merge(appUser);

    }

    @Override
    @Transactional
    public void delete(int id) {
        AppUser appUser= entityManager.find(AppUser.class,id);
        if (appUser!= null) entityManager.remove(appUser);
        else throw new IllegalArgumentException("no Item found to remove");

    }
}
