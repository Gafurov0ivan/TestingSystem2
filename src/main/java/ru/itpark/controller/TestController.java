package ru.itpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ru.itpark.model.Question;
import ru.itpark.model.Test;
import ru.itpark.model.UserAnswer;
import ru.itpark.model.UserTest;
import ru.itpark.service.TestService;
import ru.itpark.service.UserTestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author Kamila Iskhakova
 *         Created on 07.11.2016
 *  Прохождение теста
 */
@Controller
public class TestController {

  @Autowired
  private TestService testService;

  @Autowired
  private UserTestService userTestService;

  @RequestMapping(value = "/test", method = RequestMethod.GET)
  public ModelAndView getTest(WebRequest request) {
    ModelAndView modelAndView = new ModelAndView();
    String idStr = request.getParameter("id");
    if (idStr != null && !idStr.isEmpty()) {
      Long testId = Long.parseLong(request.getParameter("id"));
      Test test = testService.getTest(testId);
      if (test != null) {
        modelAndView.addObject("denied", userTestService.isTestFinished(testId));
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
      Long id = Long.parseLong(request.getParameter("id"));
      Test test = testService.getTest(id);
      Map paramMap = request.getParameterMap();
      Integer result = testService.checkTest(paramMap, test);
      Integer questionCount = test.getQuestionCount();
      modelAndView.addObject("testResult", result);
      modelAndView.addObject("testId", id);
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
    Long testId = Long.parseLong(request.getParameter("id"));
    UserTest userTest = userTestService.getUserTest(testId);
    if (userTest != null) {
      Test test = userTest.getTest();
      List<Long> userAnswers = userTest.getUserAnswers().stream().map(ua -> ua.getUserAnswer().getId())
          .collect(Collectors.toList());
      Set<Long> correctQuestionIds = userTest.getUserAnswers().stream()
          .filter(ua -> ua.isCorrectAnswer()).map(ua -> ua.getUserAnswer().getQuestion().getId())
          .collect(Collectors.toSet());
      modelAndView.addObject("test", test);
      modelAndView.addObject("correctQuestionIds", correctQuestionIds);
      modelAndView.addObject("userAnswers", userAnswers);
    }
    modelAndView.addObject("testId", testId);
    modelAndView.setViewName("showTest");

    return modelAndView;
  }
}
