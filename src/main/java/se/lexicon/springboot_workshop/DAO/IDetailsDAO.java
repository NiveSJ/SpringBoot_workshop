package se.lexicon.springboot_workshop.DAO;

import se.lexicon.springboot_workshop.entity.AppUser;
import se.lexicon.springboot_workshop.entity.Details;

import java.util.Collection;

public interface IDetailsDAO {

    Details findById(int id);

    Collection<Details> findAll();

    Details create();

    Details update();

    void delete();
}
