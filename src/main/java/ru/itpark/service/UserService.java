package ru.itpark.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itpark.dao.UserDao;
import ru.itpark.model.User;

/**
 * @author Kamila Iskhakova
 *         Created on 08.11.2016
 */
@Service
public class UserService {

  @Autowired
  private UserDao userDao;

  public void save(User user) {
    userDao.saveOrUpdate(user);
  }


  /**
   * Поиск пользователя с логином и паролем
   * @param userName
   * @param password
   * @return
   */
  public boolean isUserValid(String userName, String password) {
    User user = userDao.getUser(userName, password);
    return user != null;
  }
}
