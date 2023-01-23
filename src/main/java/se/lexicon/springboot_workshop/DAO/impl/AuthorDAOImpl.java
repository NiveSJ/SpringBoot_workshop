package se.lexicon.springboot_workshop.DAO.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.springboot_workshop.DAO.IAuthorDAO;
import se.lexicon.springboot_workshop.entity.Author;
import se.lexicon.springboot_workshop.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class AuthorDAOImpl implements IAuthorDAO {
    @PersistenceContext
    EntityManager entityManager;



    @Override
    @Transactional
    public Author findById(int id) {
        if(id == 0) throw new IllegalArgumentException(">> From Author DAO: Id Cannot be null");
        return entityManager.find(Author.class,id);
    }

    @Override
    public Collection<Author> findAll() {
        return entityManager.createNamedQuery("findAll").getResultList();
    }

    @Override
    public Author create(Author author) {
        if (author == null) throw new IllegalArgumentException("Author Cannot be null");

        entityManager.persist(author);
        return author;
    }

    @Override
    public Author update(Author author) {
        if (author == null) throw new IllegalArgumentException("Author Cannot be null");

        return entityManager.merge(author);

    }

    @Override
    public void delete(int id) {
        Author author= entityManager.find(Author.class,id);
        if (author!= null) entityManager.remove(author);
        else throw new IllegalArgumentException("no Item found to remove");

    }
}
