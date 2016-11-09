package ru.itpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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
    return "hello";
  }

  @RequestMapping(value = "/userTest")
  public ModelAndView getUserTests() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("userTests");
    return modelAndView;
  }
}
