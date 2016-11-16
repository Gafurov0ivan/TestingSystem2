package ru.itpark.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Kamila Iskhakova
 *         Created on 08.11.2016
 */
@Entity
public class Question extends BaseEntity {

  private Test test;
  private String question;
  private Integer answerCount;
  private List<Answer> answers;

  public Question() {
  }

  public Question(Test test) {
    this.test = test;
  }

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

  @OneToMany(mappedBy = "question", fetch = FetchType.LAZY,
          cascade = CascadeType.PERSIST, orphanRemoval = true)
  @OrderBy("id")
  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }

  public void addAnswer(Answer answer){
    answers.add(answer);
  }

  public void removeAllAnswers(){
    answers.clear();
  }
}
