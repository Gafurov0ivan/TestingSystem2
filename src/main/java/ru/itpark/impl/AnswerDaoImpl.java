package ru.itpark.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import ru.itpark.dao.AnswerDao;
import ru.itpark.model.Answer;

public class AnswerDaoImpl implements AnswerDao {
	  @Transactional
	public List getAnswers(Long questionId) {
		return null;
	}

	public Answer findAnswerByQuestion(Long questionId) {
		return null;
	}

	public void saveAnswer(Answer answer) {
	}

	public void deleteAnswerbyQuestion(Long questionId) {
	}

	@Override
	public void saveOrUpdate(Object entity) {
	}

	@Override
	public void remove(Object entity) {
	}
}
