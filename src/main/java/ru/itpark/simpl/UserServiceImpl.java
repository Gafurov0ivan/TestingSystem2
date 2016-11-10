package ru.itpark.simpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itpark.dao.UserDao;
import ru.itpark.model.User;
import ru.itpark.service.UserService;

/**
 * @author Kamila Iskhakova
 *         Created on 10.11.2016
 */
@Service
public class UserServiceImpl implements UserService{

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
