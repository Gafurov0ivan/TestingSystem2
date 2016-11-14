package ru.itpark.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.dao.QuestionDao;
import ru.itpark.model.Question;
import ru.itpark.model.Test;

import javax.persistence.Query;
import java.util.List;

/**
 * @author Kamila Iskhakova
 *         Created on 08.11.2016
 */
@Repository
public class QuestionDaoImpl extends BaseDaoImpl implements QuestionDao {

  public QuestionDaoImpl() {
    super(Question.class);
  }

  @Transactional
  @Override
  public List<Question> getQuestionsByTestId(Long testId) {
    Query q = getEntityManager().createQuery("SELECT t FROM Question t " +
            "where t.test.id=:testId");
    q.setParameter("testId", testId);
    return (List<Question>) q.getResultList();
  }
}
