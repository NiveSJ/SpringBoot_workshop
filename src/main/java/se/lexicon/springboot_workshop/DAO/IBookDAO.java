package se.lexicon.springboot_workshop.DAO;

import se.lexicon.springboot_workshop.entity.Book;
import se.lexicon.springboot_workshop.entity.Details;

import java.util.Collection;

public interface IBookDAO {
    Book findById(int id);

    Collection<Book> findAll();

    Book create(Book book);

    Book update(Book book);

    void delete(int id);
}
