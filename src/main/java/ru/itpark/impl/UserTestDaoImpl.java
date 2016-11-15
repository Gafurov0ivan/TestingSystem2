package ru.itpark.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.dao.UserTestDao;
import ru.itpark.model.UserTest;

import javax.persistence.Query;
import java.util.List;

/**
 * @author Kamila Iskhakova
 *         Created on 14.11.2016
 */
@Repository
public class UserTestDaoImpl extends BaseDaoImpl implements UserTestDao {

  public UserTestDaoImpl() {
    super(UserTest.class);
  }

  @Transactional
  public List<UserTest> getCompletedTestsByUser(String userName) {
    Query q = getEntityManager().createQuery("SELECT ut FROM UserTest ut " +
        "where ut.user.userName=:userName");
    q.setParameter("userName", userName);
    return q.getResultList();
  }

  @Transactional
  public UserTest getCompletedTest(Long testId, String userName) {
    Query q = getEntityManager().createQuery("SELECT ut FROM UserTest ut " +
        "where ut.test.id = :testId and ut.user.userName=:userName");
    q.setParameter("testId", testId);
    q.setParameter("userName", userName);
    q.setMaxResults(1);
    List<UserTest> userTests = (List<UserTest>)q.getResultList();
    return userTests.stream().findFirst().orElse(null);
  }
}
