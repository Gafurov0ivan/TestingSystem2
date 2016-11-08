package ru.itpark.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itpark.dao.UserDao;
import ru.itpark.entity.User;

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

  public User getUser(String userName) {
    return userDao.getUserByUserName(userName);
  }

  public boolean checkUserValid(String userName, String password) {
    User user = userDao.getUserByUserName(userName);
    return user.getPassword().equals(password);
  }
}
