package ru.itpark.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * @author Kamila Iskhakova
 *         Created on 09.11.2016
 */
@Entity
public class UserAuthorTest extends BaseEntity {

  private User user;
  private Test test;

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
}
