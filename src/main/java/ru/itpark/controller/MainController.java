package ru.itpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itpark.entity.User;
import ru.itpark.service.UserService;


/**
 * @author Kamila Iskhakova
 *         Created on 07.11.2016
 */
@Controller
public class MainController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/")
  public String goToHelloPage() {
   // userService.save(new User(3L, "Vova"));
    return "hello";
  }
}
