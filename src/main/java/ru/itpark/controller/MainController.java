package ru.itpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itpark.model.Answer;
import ru.itpark.model.Question;
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
    Test test = new Test();
    test.setCaption("caption");
    test.setQuestionCount(2);
    List<Question> list = new ArrayList<>(2);
    Question q1 = new Question();
   // q1.setId(33L);
    q1.setTest(test);
    q1.setQuestion("question1");
    List<Answer> answers1 = new ArrayList<>();
    Answer a11 = new Answer( q1, "answer11", true);
    answers1.add(a11);
    Answer a12 = new Answer(q1, "answer12", true);
    answers1.add(a12);
    Answer a13 = new Answer(q1, "answer13", false);
    answers1.add(a13);
    q1.setAnswers(answers1);
    q1.setAnswerCount(2);
    list.add(q1);
    // ---
    Question q2 = new Question();
    q2.setTest(test);
    q2.setId(54L);
    q2.setQuestion("question2");
    q2.setAnswerCount(1);
    List<Answer> answers2 = new ArrayList<>();
    Answer a21 = new Answer( q1, "answer21", false);
    answers2.add(a21);
    Answer a22 = new Answer( q1, "answer22", true);
    answers2.add(a22);
    Answer a23 = new Answer( q1, "answer23", false);
    answers2.add(a23);
    q2.setAnswers(answers2);
    list.add(q2);
    test.setQuestions(list);
    testService.save(test);
    modelAndView.addObject("test", test);
    modelAndView.setViewName("test");
    return modelAndView;
  }
}
