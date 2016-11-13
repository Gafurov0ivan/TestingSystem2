package ru.itpark.service;

import org.springframework.stereotype.Service;
import ru.itpark.model.Test;
import ru.itpark.model.UserTest;

import java.util.List;

/**
 * @author Kamila Iskhakova
 *         Created on 09.11.2016
 */
@Service
public interface TestService {

  List<Test> getTests(String userName);
  List<UserTest> getCompletedTestsByUser(String userName);
  String removeAll(List<Long> ids);
  Test getTest(Long id);
  void save(Test test);
}
