package ru.itpark.dao;

/**
 * @author Kamila Iskhakova
 *         Created on 08.11.2016
 */
public interface BaseDao<E> {

  void saveOrUpdate(E entity);
}
