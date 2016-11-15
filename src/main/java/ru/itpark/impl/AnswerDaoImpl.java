package ru.itpark.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.dao.AnswerDao;
import ru.itpark.model.Answer;
import ru.itpark.model.Question;

import javax.persistence.Query;

@Repository
public class AnswerDaoImpl extends BaseDaoImpl implements AnswerDao {

	public AnswerDaoImpl() {
		super(Answer.class);
	}

	@Transactional
	public List getAnswersByQuestionId(Long questionId) {
		Query q = getEntityManager().createQuery("SELECT t FROM Answer t " +
				"where t.question.id=:questionId");
		q.setParameter("questionId", questionId);
		return (List<Question>) q.getResultList();
	}

	@Override
	public List<Long> getAnswerIdsByQuestionId(Long questionId) {
		Query q = getEntityManager().createQuery("SELECT t.id FROM Answer t " +
				"where t.question.id=:questionId");
		q.setParameter("questionId", questionId);
		return (List<Long>) q.getResultList();
	}
}
