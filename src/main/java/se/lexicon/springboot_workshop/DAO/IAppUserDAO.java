package se.lexicon.springboot_workshop.DAO;

import se.lexicon.springboot_workshop.entity.AppUser;

import java.util.Collection;

public interface IAppUserDAO {

    AppUser findById(int id);

    Collection<AppUser> findAll();

    AppUser create();

    AppUser update();

    void delete();


}
