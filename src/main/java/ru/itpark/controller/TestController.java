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
import java.util.Map;


/**
 * @author Kamila Iskhakova
 *         Created on 07.11.2016
 *  Прохождение теста
 */
@Controller
public class TestController {

  @Autowired
  private TestService testService;

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
      Integer result = testService.checkTest(paramMap, test);
      Integer questionCount = test.getQuestionCount();
      modelAndView.addObject("testResult", result);
      modelAndView.addObject("testId", test.getId());
      modelAndView.addObject("testResultPercent", result*100/questionCount);
      modelAndView.addObject("testCaption", test.getCaption());
      modelAndView.addObject("questionCount", questionCount);
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

  @RequestMapping(value = "/showTest", method = RequestMethod.GET)
  public ModelAndView showTestCorrectResults(HttpServletRequest request) {
    // todo Отображать только если пользователь прошел тест
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("showTest");
    return modelAndView;
  }
}
