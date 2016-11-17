package ru.itpark.service;

import ru.itpark.model.User;

/**
 * @author Kamila Iskhakova
 *         Created on 08.11.2016
 */
public interface UserService {

  public void save(User user);

  /**
   * Поиск пользователя с логином и паролем
   */
  public boolean isUserValid(String userName, String password);

  public User getUserById(Long id);
}
