package ru.itpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ru.itpark.model.Test;
import ru.itpark.model.UserAnswer;
import ru.itpark.model.UserTest;
import ru.itpark.service.TestService;
import ru.itpark.service.UserTestService;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.itpark.util.RequestUtil.getId;

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
    Long testId = getId(request.getParameter("id"));
    if (testId != null) {
      Test test = testService.getTest(testId);
      if (test != null) {
        modelAndView.addObject("denied", userTestService.isTestFinished(testId, "Kamila"));
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
  public ModelAndView getPostTest(WebRequest request) {
    ModelAndView modelAndView = new ModelAndView();
    Long id = getId(request.getParameter("id"));
    if (id != null ) {
      boolean hasFinished = userTestService.isTestFinished(id, "Kamila");
      Test test = testService.getTest(id);
      if (!hasFinished) {
        Map paramMap = request.getParameterMap();
        Integer result = testService.checkTest(paramMap, test);
        Integer questionCount = test.getQuestionCount();
        modelAndView.addObject("testResult", result);
        modelAndView.addObject("testId", id);
        modelAndView.addObject("testResultPercent", result*100/questionCount);
        modelAndView.addObject("testCaption", test.getCaption());
        modelAndView.addObject("questionCount", questionCount);
      } else {
        modelAndView.addObject("denied", true);
        modelAndView.addObject("testCaption", test.getCaption());
      }
    }
    modelAndView.setViewName("result");
    return modelAndView;
  }

  @RequestMapping(value = "/showTest", method = RequestMethod.GET)
  public ModelAndView showTestCorrectResults(WebRequest request) {
    // todo Отображать только если пользователь прошел тест
    ModelAndView modelAndView = new ModelAndView();
    Long testId = getId(request.getParameter("id"));
    if (testId != null) {
      UserTest userTest = userTestService.getUserTest(testId, "Kamila");
      if (userTest != null) {
        Test test = userTest.getTest();
        List<Long> userAnswers = userTest.getUserAnswers().stream().map(ua -> ua.getUserAnswer().getId())
            .collect(Collectors.toList());
        Set<Long> correctQuestionIds = userTest.getUserAnswers().stream()
            .filter(UserAnswer::isCorrectAnswer)
            .map(ua -> ua.getUserAnswer().getQuestion().getId())
            .collect(Collectors.toSet());
        modelAndView.addObject("test", test);
        modelAndView.addObject("correctQuestionIds", correctQuestionIds);
        modelAndView.addObject("userAnswers", userAnswers);
        modelAndView.setViewName("showTest");
      }
      modelAndView.addObject("testId", testId);
    } else {
      modelAndView.setViewName("errorpage");
    }
    return modelAndView;
  }
}
