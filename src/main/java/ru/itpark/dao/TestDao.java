package ru.itpark.dao;

import ru.itpark.entity.Test;

import java.util.List;

/**
 * @author Kamila Iskhakova
 *         Created on 08.11.2016
 */
public interface TestDao<E> extends BaseDao<E>{

  List<Test> getTestsByAuthor(Long authorId);
}
