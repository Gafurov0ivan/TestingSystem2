package ru.itpark.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.dao.TestDao;
import ru.itpark.model.Test;

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
    //todo
    return null;
  }

}
