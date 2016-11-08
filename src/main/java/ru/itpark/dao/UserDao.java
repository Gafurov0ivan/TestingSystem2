package ru.itpark.dao;

import ru.itpark.entity.User;

import java.util.List;

/**
 * @author Kamila Iskhakova
 *         Created on 07.11.2016
 */

public interface UserDao<E> extends BaseDao<E> {

  List<User> getAllUsers();
}
