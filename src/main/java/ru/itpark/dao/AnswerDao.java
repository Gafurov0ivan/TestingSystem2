package ru.itpark.dao;

import java.util.List;

import ru.itpark.model.Answer;

public interface AnswerDao<E> extends BaseDao<E> {
	List<Answer> getAnswersByQuestionId(Long questionId);
	List<Long> getAnswerIdsByQuestionId(Long questionId);
}
