package ru.itpark.dao;

import ru.itpark.model.User;

/**
 * @author Kamila Iskhakova
 *         Created on 07.11.2016
 */

public interface UserDao<E> extends BaseDao<E> {

//  List<User> getAllUsers();
//
//  User getUser(String userName);
//
  User getUser(String userName, String password);

  User getUser(String userName);


  //  User findByUserName(String userName);
  User save(String userName, String password);
//  void deleteByUserName(String userName);
//  List<User> findAllUsers();
}
