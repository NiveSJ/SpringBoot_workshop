package se.lexicon.springboot_workshop.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name="AppUser.findAll",query = "FROM AppUser ")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appUserId;

    @Column(unique = true)
    private String userName;

    private String password;
    @Column(nullable = false)
    private LocalDate regDate;
    // if app User is removed then details has to be removed
    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH, CascadeType.REMOVE,CascadeType.PERSIST})
    @JoinColumn(name = "details_id")     // Making foreign key here
    private Details details;


    // Owner class. So we need to populate list through convenience method
    @OneToMany (mappedBy = "borrower",
            cascade =
                    {CascadeType.REMOVE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})

    private List<BookLoan> loan;


    public AppUser() {

    }

    public AppUser(String userName, String password, LocalDate regDate, Details details) {
        this.userName = userName;
        this.password = password;
        this.regDate = regDate;
         setDetails(details);
    }

    public AppUser(String userName, String password, LocalDate regDate) {
        this.userName = userName;
        this.password = password;
        this.regDate = regDate;

    }

    public AppUser(int appUserId, String userName, String password, LocalDate regDate, Details details) {
        this.appUserId = appUserId;
        this.userName = userName;
        this.password = password;
        this.regDate = regDate;
        this.details = details;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }


    //  private List<BookLoan> loan;

    public void addBookLoan(BookLoan bookLoan)

    {
        if(bookLoan == null) throw new IllegalArgumentException("Book Loan field cannot be empty");
        if(loan.isEmpty()) loan= new ArrayList<>(); // To handle null Pointer exception

        loan.add(bookLoan);
        //Since it is two way need to populate AppUser field in book Loan
        bookLoan.setBorrower(this);

    }



    public void removeBookLoan(BookLoan bookLoan)
    {
        if(bookLoan == null) throw new IllegalArgumentException("Book Loan field cannot be empty");
      // Need to make App user null the remove from list
        bookLoan.setBorrower(null);
        loan.remove(bookLoan);
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return appUserId == appUser.appUserId && Objects.equals(userName, appUser.userName) && Objects.equals(password, appUser.password) && Objects.equals(regDate, appUser.regDate) && Objects.equals(details, appUser.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appUserId, userName, password, regDate, details);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "appUserId=" + appUserId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", regDate=" + regDate +
                ", details=" + details +
                '}';
    }
}
