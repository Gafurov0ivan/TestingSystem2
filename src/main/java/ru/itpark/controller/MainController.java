package ru.itpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ru.itpark.model.Test;
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
  public ModelAndView getPostUserTests(HttpServletRequest request) {
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

  @RequestMapping(value = "/completedTests", method = RequestMethod.GET)
  public ModelAndView getCompletedTests() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("completedTests");
    modelAndView.addObject("tests", testService.getCompletedTestsByUser("Kamila"));
    return modelAndView;
  }

  @RequestMapping(value = "/test", method = RequestMethod.GET)
  public ModelAndView getTest(WebRequest request) {
    ModelAndView modelAndView = new ModelAndView();
    String idStr = request.getParameter("id");
    if (idStr != null && !idStr.isEmpty()) {
      Long testId = Long.parseLong(request.getParameter("id"));
      Test test = testService.getTest(testId);
      if (test != null) {
        modelAndView.addObject("test", test);
        modelAndView.setViewName("test");
      } else {
        modelAndView.setViewName("errorpage");
      }
    } else {
      modelAndView.setViewName("errorpage");
    }
    return modelAndView;
  }

  @RequestMapping(value = "/test", method = RequestMethod.POST)
  public ModelAndView getPostTest(HttpServletRequest request) {
    ModelAndView modelAndView = new ModelAndView();
    String idStr = request.getParameter("id");
    if (idStr != null && !idStr.isEmpty()) {
      Long testId = Long.parseLong(request.getParameter("id"));
      Test test = testService.getTest(testId);
      Map paramMap = request.getParameterMap();
      modelAndView.addObject("testResult", testService.checkTest(paramMap, test));
      modelAndView.addObject("testCaption", test.getCaption());
      modelAndView.addObject("questionCount", test.getQuestionCount());
    }
    modelAndView.setViewName("result");
    return modelAndView;
  }

  @RequestMapping(value = "/result", method = RequestMethod.POST)
  public ModelAndView getResult(HttpServletRequest request) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("result");
    return modelAndView;
  }

}
