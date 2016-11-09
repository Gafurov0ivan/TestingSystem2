package ru.itpark.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * @author Kamila Iskhakova
 *         Created on 08.11.2016
 */
@Entity
public class Question extends BaseEntity {

  private Test test;            // ссылка на тест
  private String question;      // вопрос
  private Integer answerCount;  // количество правльных ответов
  private Collection<Answer> answers;

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

  public Integer getAnswerCount() {
    return answerCount;
  }

  public void setAnswerCount(Integer answerCount) {
    this.answerCount = answerCount;
  }

  @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
  public Collection<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(Collection<Answer> answers) {
    this.answers = answers;
  }
}
