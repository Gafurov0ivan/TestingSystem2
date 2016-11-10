package ru.itpark.proxy;

import java.util.Date;

/**
 * @see ru.itpark.model.UserTest
 */
public class UserTestProxy {

  private Integer result;
  private Integer questionCount;
  private Integer resultPercent;
  private String date;
  private String testCaption;

  public String getTestCaption() {
    return testCaption;
  }

  public void setTestCaption(String testCaption) {
    this.testCaption = testCaption;
  }

  public Integer getResult() {
    return result;
  }

  public void setResult(Integer result) {
    this.result = result;
  }

  public Integer getResultPercent() {
    return resultPercent;
  }

  public void setResultPercent(Integer resultPercent) {
    this.resultPercent = resultPercent;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Integer getQuestionCount() {
    return questionCount;
  }

  public void setQuestionCount(Integer questionCount) {
    this.questionCount = questionCount;
  }
}
