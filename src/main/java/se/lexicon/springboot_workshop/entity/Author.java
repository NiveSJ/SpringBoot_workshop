package se.lexicon.springboot_workshop.entity;

import javax.persistence.*;
import java.util.Set;
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;
   @Column(nullable = false,length = 30)
    private String firstName;
    @Column(nullable = false,length = 30)
    private String lastName;

    @ManyToMany
    private Set<Book> writtenBook;

    public Author() {
    }
}
