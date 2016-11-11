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


  List<Test> getAll();
  List<UserTest> getCompletedTestsByUser(String userName);
  void removeAll(List<Long> ids);
}
