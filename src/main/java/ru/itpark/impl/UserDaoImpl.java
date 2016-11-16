package ru.itpark.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.dao.UserDao;
import ru.itpark.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Kamila Iskhakova
 *         Created on 08.11.2016
 */

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao  {

  @PersistenceContext
  private EntityManager em;

//    @Transactional
//    public User findByUserName(String userName) {
//        return (User) em.createQuery(
//                "SELECT u FROM User u WHERE u.userName= :userName")
//                .setParameter("userName", userName)
//                .setParameter("userName", userName)
//                .getSingleResult();
//    }

  @Transactional
  public User save(String userName, String password) {
    User u = em.find(User.class, userName);
    if(u!=null)
    {
      return u;
    }
    u = new User(userName, password);
    em.persist(u);
    return u;
  }


//    @Override
//    public void deleteByUserName(String userName) {
//
//    }
//
//    @Override
//    public List<User> findAllUsers() {
//        return em
//                .createQuery("FROM User", User.class)
//                .getResultList();
//    }
//
//    public User getUser(String userName, String password) {
//
//        return (User) em.createQuery(
//                "SELECT u FROM User u WHERE u.userName= :userName and u.password=:password")
//                .setParameter("userName", userName)
//                .setParameter("password", password)
//                .getSingleResult();
//    }
//
//    public User getUser(String userName) {
//        return (User)em.createQuery(
//                "SELECT u FROM User u WHERE u.userName= :userName")
//                .setParameter("userName", userName)
//                .getSingleResult();
//    }


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

  @Override
  public User getUser(String userName) {
    return (User)getEntityManager().createQuery(
        "SELECT u FROM User u WHERE u.userName= :userName")
        .setParameter("userName", userName)
        .getSingleResult();
  }
}
