package ru.itpark.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * @author Kamila Iskhakova
 *         Created on 09.11.2016
 */
@Entity
public class UserTest extends BaseEntity {

  private User user;
  private Test test;
  private Date date;
  private Integer result;
  private List<UserAnswer> userAnswers;

  @ManyToOne(fetch = FetchType.LAZY)
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public Test getTest() {
    return test;
  }

  public void setTest(Test test) {
    this.test = test;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Integer getResult() {
    return result;
  }

  public void setResult(Integer result) {
    this.result = result;
  }

  @OneToMany(mappedBy = "userTest", fetch = FetchType.LAZY,
      cascade = CascadeType.PERSIST, orphanRemoval = true)
  public List<UserAnswer> getUserAnswers() {
    return userAnswers;
  }

  public void setUserAnswers(List<UserAnswer> userAnswers) {
    this.userAnswers = userAnswers;
  }
}
