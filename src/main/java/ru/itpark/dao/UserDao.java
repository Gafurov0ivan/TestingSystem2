package ru.itpark.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.entity.User;

import java.util.List;

/**
 * @author Kamila Iskhakova
 *         Created on 07.11.2016
 */
@Repository
public class UserDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Transactional
  public List<User> getAllUsers(){
    return getSession().createCriteria(User.class).list();
  }

  @Transactional
  public void save(User user) {
    getSession().saveOrUpdate(user);
  }

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }
}
