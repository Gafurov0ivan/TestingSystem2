package ru.itpark.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * @author Kamila Iskhakova
 *         Created on 08.11.2016
 */
@Entity
public class Question extends BaseEntity {

  private Test test;            // ссылка на тест
  private String question;      // вопрос
  private Integer answerCount;  // количество правльных ответов
  //  private Collection<Answer> answers;

  @ManyToOne(fetch = FetchType.LAZY)
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
