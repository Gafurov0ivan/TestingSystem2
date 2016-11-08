package ru.itpark.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.dao.TestDao;
import ru.itpark.entity.Test;

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

  @Transactional
  public List<Test> getTestsByAuthor(Long authorId) {
    Criteria criteria = getCriteria();
    criteria.createAlias("author", "author");
    criteria.add(Restrictions.eq("author.id", authorId));
    return criteria.list();
  }

}
