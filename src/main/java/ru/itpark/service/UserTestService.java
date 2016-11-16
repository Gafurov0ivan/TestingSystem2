package ru.itpark.service;

import org.springframework.stereotype.Service;
import ru.itpark.model.Test;
import ru.itpark.model.User;
import ru.itpark.model.UserTest;

import java.util.List;
import java.util.Set;

/**
 * @author Kamila Iskhakova
 *         Created on 15.11.2016
 */

public interface UserTestService {

  void saveUserAnswers(Test test, User user, Set<Long> correctQuestions, Set<Long> userAnswers);
  UserTest getUserTest(Long testId, String userName);
  List<UserTest> getCompletedTestsByUser(String userName);
  boolean isTestFinished(Long testId);
  boolean isTestFinished(Long testId, String userName);
}
