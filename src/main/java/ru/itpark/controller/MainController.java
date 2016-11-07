package ru.itpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itpark.dao.UserDao;
import ru.itpark.entity.User;

import java.util.List;

/**
 * @author Kamila Iskhakova
 *         Created on 07.11.2016
 */
@Controller
public class MainController {

  @Autowired
  private UserDao userDao;

  @RequestMapping(value = "/hello")
  public ModelAndView showHelloPage() {
    return new ModelAndView("hello");
  }

  @RequestMapping(value = "/")
  public ModelAndView showStart() {
    //List<User> users = userDao.getAllUsers();
    userDao.save(new User(1L, "Kamila"));
    return new ModelAndView("hello");
  }
}
