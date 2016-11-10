package ru.itpark.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.itpark.model.User;
import ru.itpark.service.TestService;

import java.io.IOException;


/**
 * @author Kamila Iskhakova
 *         Created on 07.11.2016
 */
@Controller
public class MainController {

  @Autowired
  private TestService testService;

  @RequestMapping(value = "/lk")
  public ModelAndView getLK() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("lk");
    return modelAndView;
  }

  @RequestMapping(value = "/userTests")
  public ModelAndView getUserTests() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("userTests");
    modelAndView.addObject("tests", testService.getAll());
    return modelAndView;
  }

  @RequestMapping(value = "/completedTests")
  public ModelAndView getCompletedTests() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("completedTests");
    modelAndView.addObject("tests", testService.getCompletedTestsByUser("Kamila"));
    return modelAndView;
  }

  @RequestMapping(value = "/newTest")
  public ModelAndView newTest() {
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("newTest");
      return modelAndView;
    }

    /** http://localhost:8080/newTest?testObject={%22id%22:1,%22userName%22:%22Marat%22,%22password%22:null}
     * строка такого вида нормально десереализуется
     */
  @RequestMapping(value = "/newTest", params = {"testObject"})
  public @ResponseBody void getjson(@RequestParam(value = "testObject") String jsonString) {
      ObjectMapper mapper = new ObjectMapper();
      try {
          User user = mapper.readValue(jsonString,User.class);
          System.out.println(user.getId() + user.getUserName() + user.getPassword());
      } catch (IOException e) {
          e.printStackTrace();
      }
        System.out.println(jsonString);
    }

}
