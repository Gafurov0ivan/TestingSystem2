package ru.itpark.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.Date;

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

  @Transient
  public Integer getResultPercent() {
    return  (result * 100) / test.getQuestionCount();
  }

  public void setResultPercent(Integer resultPercent) {
  }
}
