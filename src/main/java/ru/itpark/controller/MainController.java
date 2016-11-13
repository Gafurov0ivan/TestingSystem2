package ru.itpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itpark.service.TestService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author Kamila Iskhakova
 *         Created on 07.11.2016
 */
@Controller
public class MainController {

  @Autowired
  private TestService testService;

  @RequestMapping(value = "/userTests", method = RequestMethod.POST)
  public ModelAndView getUserTests(HttpServletRequest request) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("userTests");
    Map paramMap = request.getParameterMap();
    if (paramMap.containsKey("delete") && paramMap.containsKey("id")) {
      String[] srtIds = (String[])request.getParameterMap().get("id");
      List<Long> ids = new ArrayList<>(srtIds.length);
      for (String strId : srtIds) {
        ids.add(Long.parseLong(strId));
      }
      modelAndView.addObject("delRes", testService.removeAll(ids));
    }
    modelAndView.addObject("tests", testService.getTests("Kamila"));
    return modelAndView;
  }

  @RequestMapping(value = "/userTests", method = RequestMethod.GET)
  public ModelAndView getUserTests() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("userTests");
    modelAndView.addObject("tests", testService.getTests("Kamila"));
    return modelAndView;
  }

  @RequestMapping(value = "/completedTests")
  public ModelAndView getCompletedTests() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("completedTests");
    modelAndView.addObject("tests", testService.getCompletedTestsByUser("Kamila"));
    return modelAndView;
  }

  @RequestMapping(value = "/test", method = RequestMethod.GET)
  public ModelAndView getTest() {
    ModelAndView modelAndView = new ModelAndView();
   // modelAndView.addObject("test", testService.getTest(16L));
    modelAndView.setViewName("test");
    return modelAndView;
  }
}
