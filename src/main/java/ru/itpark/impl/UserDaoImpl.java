package ru.itpark.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.dao.UserDao;
import ru.itpark.model.User;

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

  @Override
  public User getUser(String userName, String password) {
    return (User)getEntityManager().createQuery(
        "SELECT u FROM User u WHERE u.userName= :userName and u.password=:password")
        .setParameter("userName", userName)
        .setParameter("password", password)
        .getSingleResult();
  }
}
