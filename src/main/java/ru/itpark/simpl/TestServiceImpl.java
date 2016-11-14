package ru.itpark.simpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.dao.TestDao;
import ru.itpark.dao.UserTestDao;
import ru.itpark.model.Answer;
import ru.itpark.model.Question;
import ru.itpark.model.Test;
import ru.itpark.model.User;
import ru.itpark.model.UserTest;
import ru.itpark.service.TestService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Kamila Iskhakova
 *         Created on 10.11.2016
 */
@Service
public class TestServiceImpl implements TestService {

  @Autowired
  private TestDao testDao;

  @Autowired
  private UserTestDao userTestDao;


  public List<Test> getTests(String userName) {
    return testDao.getTestsByAuthor(userName);
  }

  public void save(Test test) {
    testDao.saveOrUpdate(test);
  }

  @Transactional
  public List<UserTest> getCompletedTestsByUser(String userName) {
    return testDao.getCompletedTestsByUser(userName);
  }

  @Transactional
  public String removeAll(List<Long> ids) {
    StringBuilder stringBuilder = new StringBuilder();
    List<Long> notDeletedIds = new ArrayList<>();
    int count = 0;
    for (Long id : ids) {
      if (testDao.getCompletedTestsCount(id) == 0L) {
        testDao.remove(id);
        count++;
      } else {
        notDeletedIds.add(id);
      }
    }
    stringBuilder.append(count).append(" test(s) deleted.\n");
    notDeletedIds.stream().forEach(id -> {
      stringBuilder.append("Test id = ").append(id).append(" can't be deleted! Some users already have completed it.\n");
    });
    return stringBuilder.toString();
  }

  @Transactional
  public Test getTest(Long id) {
    return (Test)testDao.find(id);
  }


  public int checkTest(Map requestParam, Test test) {
    int count = 0;
    if (test != null) {
      Map<Long, Set<Long>> answerMap = getAnswerMap((List) test.getQuestions());
      for (Object key : requestParam.keySet()) {
        if (key.equals("testId") || key.equals("id")) {
          continue;
        }
        Long questionId = Long.parseLong((String) key);
        String[] answers = (String[]) requestParam.get(key);
        Set<Long> userAnswers = new HashSet<>();
        for (String userAnswer : answers) {
          userAnswers.add(Long.parseLong(userAnswer));
        }
        Set<Long> correctAnswers = answerMap.get(questionId);
        if (correctAnswers.equals(userAnswers)) {
          count++;
        }
      }
      //saveFinishedTestInfo(test, userDao.getUser("Kamila"), count);
    }

    return count;
  }



  private Map<Long, Set<Long>> getAnswerMap(List<Question> questions) {
    Map<Long, Set<Long>> result = new HashMap<>();
    for (Question question : questions) {
      result.put(question.getId(), getCorrectAnswers(question));
    }
    return result;
  }

  private Set<Long> getCorrectAnswers(Question question) {
    return question.getAnswers().stream().filter(answer -> answer.getIsCorrect())
        .map(Answer::getId).collect(Collectors.toSet());
  }

  private void saveFinishedTestInfo(Test test, User user, int result) {
    UserTest userTest = new UserTest();
    userTest.setDate(new Date());
    userTest.setResult(result);
    userTest.setTest(test);
    userTest.setUser(user);
    userTestDao.saveOrUpdate(userTest);
  }
}
