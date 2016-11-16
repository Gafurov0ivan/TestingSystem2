package ru.itpark.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.List;

/**
 * @author Kamila Iskhakova
 *         Created on 08.11.2016
 */
@Entity
public class Test extends BaseEntity {

  private List<Question> questions;
  private User author;
  private String caption;

  public Test(String caption) {
    this.caption = caption;
  }

  public Test() {
  }

  @OneToMany(mappedBy = "test", fetch = FetchType.LAZY,
          cascade = CascadeType.PERSIST, orphanRemoval = true)
  @OrderBy("id")
  public List<Question> getQuestions() {
    return questions;
  }

  public Question getQuestion(int id){
    return questions.get(id);
  }

  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public Integer getQuestionCount() {
    return questions.size();
  }

  public void setQuestionCount(Integer questionCount) {}

  public void addQuestion(Question question){
    questions.add(question);
  }

  public void removeQuestion(Question q){
    questions.remove(q);
  }
}
