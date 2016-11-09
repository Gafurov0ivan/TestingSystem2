package ru.itpark.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
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
  private BigDecimal resultPercent;

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

  public BigDecimal getResultPercent() {
    return resultPercent;
  }

  public void setResultPercent(BigDecimal resultPercent) {
    this.resultPercent = resultPercent;
  }
}
