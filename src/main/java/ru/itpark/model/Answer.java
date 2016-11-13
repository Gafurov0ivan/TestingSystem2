package ru.itpark.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Answer extends BaseEntity {

	private Question question;
	private String text;
	private boolean isCorrect;

	protected Answer() {
	}

  public Answer( Question question, String text, boolean isCorrect) {

		this.text = text;
		this.isCorrect = isCorrect;
		this.question = question;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

}
