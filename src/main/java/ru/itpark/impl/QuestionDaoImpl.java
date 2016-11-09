package ru.itpark.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.dao.QuestionDao;
import ru.itpark.model.Question;

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
  public List<Question> getQuestions(Long testId){
    //todo
    return null;
  }
}
