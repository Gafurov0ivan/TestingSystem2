package ru.itpark.dao;

import java.util.List;

import ru.itpark.model.Answer;

public interface AnswerDao<E> extends BaseDao<E> {
	List<Answer> getAnswers(Long questionId);
	Answer findAnswerByQuestion(Long questionId);
	void deleteAnswerbyQuestion(Long questionId);
}
