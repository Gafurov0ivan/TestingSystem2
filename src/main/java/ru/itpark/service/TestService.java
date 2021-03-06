package ru.itpark.service;

import ru.itpark.model.Test;
import ru.itpark.model.UserTest;

import java.util.List;
import java.util.Map;

/**
 * @author Kamila Iskhakova
 *         Created on 09.11.2016
 */
public interface TestService {

  List<Test> getTests(String userName);
  void refresh(List<Long> ids, boolean show);
  void refresh(Long id);
  Test getTest(Long id);
  void save(Test test);
  int checkTest(Map map, Test test);
  List<Test> getUnfinishedTests(String userName);
}
