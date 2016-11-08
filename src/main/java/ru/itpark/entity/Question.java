package ru.itpark.entity;

import javax.persistence.Entity;

/**
 * @author Kamila Iskhakova
 *         Created on 08.11.2016
 */
@Entity
public class Question extends BaseEntity {


  private Test test;  // ссылка на тест
//  private Collection<Answer> answers;
  private String question;


  public Test getTest() {
    return test;
  }

  public void setTest(Test test) {
    this.test = test;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }
}
