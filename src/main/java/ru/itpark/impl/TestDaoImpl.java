package ru.itpark.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.dao.TestDao;
import ru.itpark.model.Test;

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
    Query q = getEntityManager().createQuery("SELECT t FROM Test t " +
        "where t.author.userName=:authorName");
    q.setParameter("authorName", authorName);
    return (List<Test>)q.getResultList();
  }


  @Transactional
  public Long getCompletedTestsCount(Long id) {
    Query q = getEntityManager().createQuery("select count(ut.id) from UserTest ut " +
        "where ut.test.id =:id");
    q.setParameter("id", id);
    return (Long) q.getSingleResult();
  }

}
