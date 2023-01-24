package se.lexicon.springboot_workshop.entity;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@NamedQuery(name="BookLoan.findAll",query = "FROM BookLoan")
public class BookLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int LoanId;

    @Column(nullable = false)
    private LocalDate loanDate;

    private LocalDate dueDate;

    private boolean returned;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "app_user_id")   // Foreign Key
    private AppUser borrower;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name= "book_id")
    private Book book;


    public BookLoan() {

    }

    public BookLoan(LocalDate loanDate, LocalDate dueDate, boolean returned, AppUser borrower, Book book) {
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returned = returned;
        this.borrower = borrower;
        this.book = book;
    }


    public BookLoan(int loanId, LocalDate loanDate, LocalDate dueDate, boolean returned, AppUser borrower, Book book) {
        LoanId = loanId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returned = returned;
        this.borrower = borrower;
        this.book = book;
    }

    public int getLoanId() {
        return LoanId;
    }

    public void setLoanId(int loanId) {
        LoanId = loanId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public AppUser getBorrower() {
        return borrower;
    }

    public void setBorrower(AppUser borrower) {
        this.borrower = borrower;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }







    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookLoan bookLoan = (BookLoan) o;
        return LoanId == bookLoan.LoanId && returned == bookLoan.returned && Objects.equals(loanDate, bookLoan.loanDate) && Objects.equals(dueDate, bookLoan.dueDate) && Objects.equals(borrower, bookLoan.borrower) && Objects.equals(book, bookLoan.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(LoanId, loanDate, dueDate, returned, borrower, book);
    }

    @Override
    public String toString() {
        return "BookLoan{" +
                "LoanId=" + LoanId +
                ", loanDate=" + loanDate +
                ", dueDate=" + dueDate +
                ", returned=" + returned +
                ", borrower=" + borrower +
                ", book=" + book +
                '}';
    }
}
