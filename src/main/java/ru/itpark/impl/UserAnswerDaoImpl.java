package ru.itpark.impl;

import org.springframework.stereotype.Repository;
import ru.itpark.dao.UserAnswerDao;
import ru.itpark.model.UserAnswer;

/**
 * @author Kamila Iskhakova
 *         Created on 15.11.2016
 */
@Repository
public class UserAnswerDaoImpl extends BaseDaoImpl implements UserAnswerDao {

  public UserAnswerDaoImpl() {
    super(UserAnswer.class);
  }
}
