package se.lexicon.springboot_workshop.entity;

import javax.persistence.*;

import java.time.LocalDate;

@Entity
@NamedQuery(name="findAll",query = "FROM BookLoan")
public class BookLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int LoanId;

    @Column(nullable = false)
    private LocalDate loanDate;

    private LocalDate dueDate;

    private boolean returned;
    @ManyToOne
    private AppUser borrower;

    @ManyToOne
    private Book book;


    public BookLoan() {

    }
}
