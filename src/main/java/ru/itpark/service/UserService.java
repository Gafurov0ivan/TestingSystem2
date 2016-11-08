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

}
