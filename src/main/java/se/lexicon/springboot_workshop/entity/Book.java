package se.lexicon.springboot_workshop.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQuery(name="Book.findAll",query = "FROM Book")

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    @Column(nullable = false,length = 100)
    private String isbn;
    @Column(nullable = false,length = 100)
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    //mappedBy = "writtenBook"
    @JoinTable(name = "book_author",joinColumns = @JoinColumn(name="book_id"),inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;

    @Column(nullable = false)
    private int maxLoanDays;

    public Book(){}

    public Book(String isbn, String title, Set<Author> authors, int maxLoanDays) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.maxLoanDays = maxLoanDays;
    }


    public Book(String isbn, String title, int maxLoanDays) {
        this.isbn = isbn;
        this.title = title;

        this.maxLoanDays = maxLoanDays;
    }

    public Book(int bookId, String isbn, String title, Set<Author> authors, int maxLoanDays) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        setAuthors(authors);
        this.maxLoanDays = maxLoanDays;
    }


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {

        if(authors == null)
            authors = new HashSet<>();

        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public int getMaxLoanDays() {
        return maxLoanDays;
    }

    public void setMaxLoanDays(int maxLoanDays) {
        this.maxLoanDays = maxLoanDays;
    }


  //  private Set<Author> authors;


    public void setAuthorsset(Author author)
    {
        if(author == null) throw new IllegalArgumentException("author cannot be null");
        if (authors == null)
            authors = new HashSet<>();
        // Populate the set present in this class
        authors.add(author);


    }

    public void removeAuthorsset(Author author){
        if(author == null) throw new IllegalArgumentException("author cannot be null");

          authors.remove(author);

    }

/*
  public void setAuthors(Author author)
  {
      if(author == null) throw new IllegalArgumentException("author cannot be null");
     if (authors == null)
         authors = new HashSet<>();
      // Populate the set present in this class
    authors.add(author);
    // set book to the author
    author.getWrittenBook().add(this);

  }

  public void removeAuthors(Author author){
      if(author == null) throw new IllegalArgumentException("author cannot be null");

      author.getWrittenBook().remove(this); // removing associated Book
      authors.remove(author);



  }*/


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId && maxLoanDays == book.maxLoanDays && Objects.equals(isbn, book.isbn) && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, isbn, title, maxLoanDays);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", maxLoanDays=" + maxLoanDays +
                '}';
    }
}
