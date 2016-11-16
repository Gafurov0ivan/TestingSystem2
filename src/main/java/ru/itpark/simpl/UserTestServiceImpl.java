package ru.itpark.simpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.dao.UserAnswerDao;
import ru.itpark.dao.UserTestDao;
import ru.itpark.model.Answer;
import ru.itpark.model.Question;
import ru.itpark.model.Test;
import ru.itpark.model.User;
import ru.itpark.model.UserAnswer;
import ru.itpark.model.UserTest;
import ru.itpark.service.UserTestService;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Kamila Iskhakova
 *         Created on 15.11.2016
 */
@Service
public class UserTestServiceImpl implements UserTestService {

  @Autowired
  private UserTestDao userTestDao;

  @Autowired
  private UserAnswerDao userAnswerDao;


  @Transactional
  public void saveUserAnswers(Test test, User user, Set<Long> correctQuestions, Set<Long> userAnswers) {
    // test finishing info
    UserTest userTest = new UserTest();
    userTest.setDate(new Date());
    userTest.setResult(correctQuestions.size());
    userTest.setTest(test);
    userTest.setUser(user);
    userTestDao.saveOrUpdate(userTest);
    // user's answers
    List<Question> questions = test.getQuestions();
    for (Question question: questions) {
      List<Answer> answers = question.getAnswers();
      for (Answer answer : answers) {
        if (userAnswers.contains(answer.getId())) {
          UserAnswer userAnswer = new UserAnswer();
          userAnswer.setUserTest(userTest);
          userAnswer.setUserAnswer(answer);
          userAnswer.setCorrectAnswer(correctQuestions.contains(question.getId()));
          userAnswerDao.saveOrUpdate(userAnswer);
        }
      }
    }
  }

  @Transactional
  public UserTest getUserTest(Long testId, String userName){
    // todo getCurrentUserName
    return userTestDao.getCompletedTest(testId, userName);
  }

  @Transactional
  public List<UserTest> getCompletedTestsByUser(String userName) {
    return userTestDao.getCompletedTestsByUser(userName);
  }

  @Transactional
  public boolean isTestFinished(Long testId) {
    return userTestDao.isTestFinished(testId);
  }

  @Transactional
  public boolean isTestFinished(Long testId, String userName) {
    return userTestDao.isTestFinished(testId, userName);
  }
}
