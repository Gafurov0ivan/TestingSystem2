package ru.itpark.model;

import javafx.beans.DefaultProperty;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * @author Kamila Iskhakova
 *         Created on 15.11.2016
 */
@Entity
public class UserAnswer extends BaseEntity {

  private Answer userAnswer;
  private UserTest userTest;
  private boolean correctAnswer;

  @ManyToOne(fetch = FetchType.LAZY)
  public Answer getUserAnswer() {
    return userAnswer;
  }

  public void setUserAnswer(Answer userAnswer) {
    this.userAnswer = userAnswer;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public UserTest getUserTest() {
    return userTest;
  }

  public void setUserTest(UserTest userTest) {
    this.userTest = userTest;
  }


  public boolean isCorrectAnswer() {
    return correctAnswer;
  }

  public void setCorrectAnswer(boolean correctAnswer) {
    this.correctAnswer = correctAnswer;
  }
}
