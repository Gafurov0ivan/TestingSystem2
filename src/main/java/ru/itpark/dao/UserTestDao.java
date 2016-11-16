package ru.itpark.dao;

import ru.itpark.model.User;
import ru.itpark.model.UserTest;

import java.util.List;

/**
 * @author Kamila Iskhakova
 *         Created on 14.11.2016
 */
public interface UserTestDao<E> extends BaseDao<E>{

  List<UserTest> getCompletedTestsByUser(String userName);
  UserTest getCompletedTest(Long testId, String userName);
  boolean isTestFinished(Long testId);
  boolean isTestFinished(Long testId, String userName);
}
