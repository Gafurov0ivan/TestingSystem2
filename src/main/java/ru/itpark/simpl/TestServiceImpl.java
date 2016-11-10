package ru.itpark.simpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.dao.TestDao;
import ru.itpark.model.Test;
import ru.itpark.model.UserTest;
import ru.itpark.proxy.UserTestProxy;
import ru.itpark.service.TestService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
  public List<UserTestProxy> getCompletedTestsByUser(String userName) {
    SimpleDateFormat sdg = new SimpleDateFormat("dd.MM.yyyy");
    List<UserTest> tests = testDao.getCompletedTestsByUser(userName);
    List<UserTestProxy> result = new ArrayList<>();
    for (UserTest test:tests) {
      UserTestProxy testProxy = new UserTestProxy();
      testProxy.setTestCaption(test.getTest().getCaption());
      if (test.getDate() != null) {
        testProxy.setDate(sdg.format(test.getDate()));
      }
      testProxy.setResult(test.getResult());
      testProxy.setResultPercent(test.getResultPercent());
      testProxy.setQuestionCount(test.getTest().getQuestionCount());
      result.add(testProxy);
    }
    return result;
  }
}
