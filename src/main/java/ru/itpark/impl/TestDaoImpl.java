package ru.itpark.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.dao.TestDao;
import ru.itpark.model.Test;
import ru.itpark.model.UserTest;

import javax.persistence.Query;
import java.util.List;

/**
 * @author Kamila Iskhakova
 *         Created on 08.11.2016
 */
@Repository
public class TestDaoImpl extends BaseDaoImpl implements TestDao {

  public TestDaoImpl() {
    super(Test.class);
  }

  @Override
  public List<Test> getTestsByAuthor(String authorName) {
    //todo
    return null;
  }

  @Transactional
  public List<UserTest> getCompletedTestsByUser(String userName) {
    Query q = getEntityManager().createQuery("SELECT ut FROM Test t, UserTest ut " +
        "where ut.test.id = t.id and ut.user.userName=:userName");
    q.setParameter("userName", userName);
    return (List<UserTest>)q.getResultList();
  }

  @Transactional
  public Long getCompletedTestsCount(Long id) {
    Query q = getEntityManager().createQuery("select count(ut.id) from UserTest ut " +
        "where ut.test.id =:id");
    q.setParameter("id", id);
    return (Long) q.getSingleResult();
  }

}
