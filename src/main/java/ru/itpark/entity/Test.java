package ru.itpark.entity;

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
public class Test extends BaseEntity {

  private Collection<Question> questions;
  private User author;
  private String caption;

  @OneToMany(mappedBy = "test", fetch = FetchType.LAZY)
  public Collection<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(Collection<Question> questions) {
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
}
