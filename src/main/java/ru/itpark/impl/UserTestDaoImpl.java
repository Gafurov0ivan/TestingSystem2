package ru.itpark.impl;

import org.springframework.stereotype.Repository;
import ru.itpark.dao.UserTestDao;
import ru.itpark.model.UserTest;

/**
 * @author Kamila Iskhakova
 *         Created on 14.11.2016
 */
@Repository
public class UserTestDaoImpl extends BaseDaoImpl implements UserTestDao {

  public UserTestDaoImpl() {
    super(UserTest.class);
  }
}
