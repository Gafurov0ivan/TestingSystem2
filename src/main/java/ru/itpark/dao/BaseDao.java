package ru.itpark.dao;

import java.util.Collection;
import java.util.List;

/**
 * @author Kamila Iskhakova
 *         Created on 08.11.2016
 */
public interface BaseDao<E> {

  void saveOrUpdate(E entity);
  void remove(E entity);
  void remove(Long id);
  void removeAll(List<Long> ids);
  E find(Long id);
  List<E> getAll();
}
