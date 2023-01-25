package se.lexicon.springboot_workshop.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQuery(name = "Author.findAll", query = "FROM Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;
    @Column(nullable = false, length = 30)
    private String firstName;
    @Column(nullable = false, length = 30)
    private String lastName;

    // Added fetch type to remove Caused by: org.hibernate.LazyInitializationException: failed to lazily initialize a
    // collection of role: se.lexicon.springboot_workshop.entity.Author.writtenBook, could not initialize proxy -
    // no Session
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_author",joinColumns = @JoinColumn(name="author_id"),inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> writtenBook;

    public Author() {
    }

    public Author(String firstName, String lastName, Set<Book> writtenBook) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.writtenBook = writtenBook;
    }


    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public Author(int authorId, String firstName, String lastName, Set<Book> writtenBook) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.writtenBook = writtenBook;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getWrittenBook() {
        if (writtenBook == null)
            writtenBook = new HashSet<>();

        return writtenBook;
    }

    public void setWrittenBook(Set<Book> writtenBook) {
        this.writtenBook = writtenBook;
    }


    //private Set<Book> writtenBook;



    public void addWrittenBook(Book book) {
        if (book == null) throw new IllegalArgumentException("Book is null");
        if (writtenBook == null) writtenBook = new HashSet<>();
        writtenBook.add(book);

    }


    public void removeWrittenBook(Book book) {

        writtenBook.remove(book);

    }
/*
    public void addWrittenBook(Book book) {
        if (book == null) throw new IllegalArgumentException("Book is null");
        if (writtenBook == null) writtenBook = new HashSet<>();
        writtenBook.add(book);
        book.getAuthors().add(this);

    }


    public void removeWrittenBook(Book book) {
        book.getAuthors().remove(this);
        writtenBook.remove(book);


    }*/


/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return authorId == author.authorId && Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName) && Objects.equals(writtenBook, author.writtenBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, firstName, lastName, writtenBook);
    }
*/

   /* @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", writtenBook=" + writtenBook +
                '}';
    }*/
}
