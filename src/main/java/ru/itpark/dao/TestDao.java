package ru.itpark.dao;

import ru.itpark.model.Test;

import java.util.List;

/**
 * @author Kamila Iskhakova
 *         Created on 08.11.2016
 */
public interface TestDao<E> extends BaseDao<E>{

  List<Test> getTestsByAuthor(String authorName);

  Long getCompletedTestsCount(Long id);

  List<Test> getUnfinishedTests(String userName);
}
