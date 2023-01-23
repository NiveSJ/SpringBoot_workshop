package se.lexicon.springboot_workshop.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    @Column(nullable = false,length = 100)
    private String isbn;
    @Column(nullable = false,length = 100)
    private String title;

    @ManyToMany(mappedBy = "writtenBook")
    private Set<Author> authors;

    @Column(nullable = false)
    private int maxLoanDays;

    public Book(){}
}
