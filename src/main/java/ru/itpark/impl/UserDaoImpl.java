package ru.itpark.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.dao.UserDao;
import ru.itpark.entity.Question;
import ru.itpark.entity.User;

import java.util.List;

/**
 * @author Kamila Iskhakova
 *         Created on 08.11.2016
 */

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao  {

  public UserDaoImpl() {
    super(User.class);
  }


  @Transactional
  public List<User> getAllUsers(){
    return getSession().createCriteria(User.class).list();
  }
}
