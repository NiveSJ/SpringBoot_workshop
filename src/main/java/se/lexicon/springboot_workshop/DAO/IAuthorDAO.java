package se.lexicon.springboot_workshop.DAO;

import se.lexicon.springboot_workshop.entity.Author;
import se.lexicon.springboot_workshop.entity.Book;

import java.util.Collection;

public interface IAuthorDAO {

    Author findById(int id);

    Collection<Author> findAll();

    Author create(Author author);

    Author update(Author author);

    void delete(int id);
}
