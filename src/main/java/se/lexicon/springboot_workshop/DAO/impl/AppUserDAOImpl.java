package se.lexicon.springboot_workshop.DAO.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.springboot_workshop.DAO.IAppUserDAO;
import se.lexicon.springboot_workshop.entity.AppUser;
import se.lexicon.springboot_workshop.entity.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
@Repository
public class AppUserDAOImpl implements IAppUserDAO {
    @PersistenceContext
    EntityManager entityManager;



    @Override
    @Transactional
    public AppUser findById(int id) {
        if(id == 0) throw new IllegalArgumentException(">> From AppUser DAO: Id Cannot be null");
        return entityManager.find(AppUser.class,id);
    }

    @Override
    public Collection<AppUser> findAll() {
        return entityManager.createNamedQuery("findAll").getResultList();
    }

    @Override
    public AppUser create(AppUser AppUser) {
        if (AppUser == null) throw new IllegalArgumentException("AppUser Cannot be null");

        entityManager.persist(AppUser);
        return AppUser;
    }

    @Override
    public AppUser update(AppUser AppUser) {
        if (AppUser == null) throw new IllegalArgumentException("AppUser Cannot be null");

        return entityManager.merge(AppUser);

    }

    @Override
    public void delete(int id) {
        AppUser AppUser= entityManager.find(AppUser.class,id);
        if (AppUser!= null) entityManager.remove(AppUser);
        else throw new IllegalArgumentException("no Item found to remove");

    }
}
