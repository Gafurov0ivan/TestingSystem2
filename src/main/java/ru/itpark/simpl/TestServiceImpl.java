package ru.itpark.simpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.dao.TestDao;
import ru.itpark.model.Test;
import ru.itpark.model.UserTest;
import ru.itpark.service.TestService;

import java.util.List;

/**
 * @author Kamila Iskhakova
 *         Created on 10.11.2016
 */
@Service
public class TestServiceImpl implements TestService {

  @Autowired
  private TestDao testDao;

  public List<Test> getAll() {
    return testDao.getAll();
  }


  @Transactional
  public List<UserTest> getCompletedTestsByUser(String userName) {
    return testDao.getCompletedTestsByUser(userName);
  }
}
