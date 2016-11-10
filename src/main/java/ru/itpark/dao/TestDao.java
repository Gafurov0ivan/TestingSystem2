package ru.itpark.dao;

import ru.itpark.model.Test;
import ru.itpark.model.UserTest;

import java.util.List;

/**
 * @author Kamila Iskhakova
 *         Created on 08.11.2016
 */
public interface TestDao<E> extends BaseDao<E>{

  List<Test> getTestsByAuthor(String authorName);
  List<UserTest> getCompletedTestsByUser(String userName);
}
