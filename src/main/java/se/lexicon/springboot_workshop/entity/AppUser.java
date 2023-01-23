package se.lexicon.springboot_workshop.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class AppUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appUserId;

    @Column(length = 30,nullable = false,unique = true)
    private String userName;

    private String password;
    @Column(nullable = false)
    private LocalDate regDate;
    @OneToOne (cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.REMOVE})
    @JoinColumn(name = "details_id")
    private Details details;



    public AppUser(){

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
}
