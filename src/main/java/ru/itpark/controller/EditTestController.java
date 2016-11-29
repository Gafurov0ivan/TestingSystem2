package ru.itpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ru.itpark.model.Answer;
import ru.itpark.model.Question;
import ru.itpark.model.Test;
import ru.itpark.model.User;
import ru.itpark.service.*;
import ru.itpark.util.RequestUtil;

import java.util.*;

/**
 * Created by maratgumarov on 11.11.16.
 */

@Controller
public class EditTestController {

    @Autowired
    private TestService testService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private UserTestService userTestService;
    @Autowired
    private UserService userService;

    ModelAndView modelAndView;
    Test test;
    Question question;
    Integer qid;

    @RequestMapping(value = "/editTest", params = {"id"})
    public ModelAndView editTestWithoutQuestionId(WebRequest webRequest) {
        Map<String, String[]> map = new TreeMap();
        map.putAll(webRequest.getParameterMap());
        test = testService.getTest(Long.parseLong(webRequest.getParameter("id")));
        if (!isTestAccessibleForUser(test)) {
            return new ModelAndView("errorpage");
        }
        if (test.getQuestions() == null || test.getQuestionCount() == 0) {
            question = new Question();
            question.setTest(test);
            test.addQuestion(question);
            question.setQuestion("");
            questionService.save(question);
        }
        map.put("questionId", new String[]{"0"});
        return new ModelAndView("redirect:/editTest", map);
    }

    @RequestMapping(value = "/editTest", params = {"id", "questionId"})
    public ModelAndView editTest(WebRequest webRequest) {
        test = testService.getTest(Long.parseLong(webRequest.getParameter("id")));
        if (!isTestAccessibleForUser(test)) {
            return new ModelAndView("errorpage");
        }
        qid = Integer.parseInt(webRequest.getParameter("questionId"));
        question = test.getQuestion(qid);
        if (qid < 0 || qid > test.getQuestionCount()) {
            return new ModelAndView("errorpage");
        }
        modelAndView = new ModelAndView();
        modelAndView.addObject("test", test);
        if (webRequest.getParameterMap().containsKey("addQuestion")) {
            Long end = new Long(test.getQuestionCount());
            Question newQuestion = new Question(test);
            test.addQuestion(newQuestion);
            testService.save(test);
            modelAndView.setViewName("editTest");
            modelAndView.addObject("qId", end);
            Map<String, String[]> map = new TreeMap();
            map.put("id", new String[]{webRequest.getParameter("id")});
            map.put("questionId", new String[]{String.valueOf(test.getQuestionCount() - 1)});
            return new ModelAndView("redirect:/editTest", map);
        }
        modelAndView.addObject("qId", new Long(qid));
        return modelAndView;
        //return new ModelAndView("errorpage");
    }

    @RequestMapping(value = "/editTest", params = {"id", "questionId", "REMOVE"})
    public ModelAndView removeQuestion(WebRequest webRequest) {
        test = testService.getTest(Long.parseLong(webRequest.getParameter("id")));
        if (!isTestAccessibleForUser(test)) {
            return new ModelAndView("errorpage");
        }
        modelAndView = editTest(webRequest);
        Map<String, String[]> map = new TreeMap();
        map.putAll(webRequest.getParameterMap());
        map.remove("REMOVE");
        if (test.getQuestionCount() > 1 && !userTestService.isTestFinished(test.getId())) {
            test.removeQuestion(question);
            testService.save(test);
            if (test.getQuestionCount() == 0) {
                String[] val = {"0"};
                map.put("questionId", val);
            } else {
                if (qid >= test.getQuestionCount()) {
                    qid = test.getQuestionCount() - 1;
                    String[] val = {qid.toString()};
                    map.put("questionId", val);
                }
            }
        }
        return new ModelAndView("redirect:/editTest", map);
    }

    @RequestMapping(value = "/editTest", method = RequestMethod.POST, params = {"saveTestCaption", "id"})
    public ModelAndView saveTestname(WebRequest webRequest) {
        test = testService.getTest(Long.parseLong(webRequest.getParameter("id")));
        if (!isTestAccessibleForUser(test)) {
            return new ModelAndView("errorpage");
        }
        Map<String, String[]> map = new TreeMap();
        map.putAll(webRequest.getParameterMap());

        Test test = testService.getTest(Long.parseLong(webRequest.getParameter("id")));
        test.setCaption(map.get("saveTestCaption")[0]);
        test.setAuthor(userService.findByUsername(RequestUtil.getCurrentUserName()));
        map.remove("saveTestCaption");
        testService.save(test);

        return new ModelAndView("redirect:/editTest", map);
    }

    @RequestMapping(value = "/editTest", params = {"id", "questionId", "SAVE"})
    public ModelAndView saveQuestion(WebRequest webRequest) {
        if (!isTestAccessibleForUser(testService.getTest(Long.parseLong(webRequest.getParameter("id"))))) {
            return new ModelAndView("errorpage");
        }

        Map<String, String[]> a = new TreeMap();
        a.putAll(webRequest.getParameterMap());

        Question question = testService.getTest(test.getId()).getQuestion(qid);

        question.setQuestion(a.get("question")[0]);

        //deleting old answers from DB
        question.removeAllAnswers();

        //Save answers from form
        List<String> correctAnswersCheckboxes = null;
        if (a.containsKey("s[]"))
            correctAnswersCheckboxes = Arrays.asList(a.get("s[]"));

        List<Answer> answers = answerService.getAnswersByQuestionId(question.getId());
        int i = 1;
        for (String key : a.keySet()) {
            if (key.matches("field\\[\\d\\d*\\]")) {
                Answer answer = new Answer();
                answer.setQuestion(question);
                answer.setText(a.get(key)[0]);
                question.addAnswer(answer);
                if (correctAnswersCheckboxes != null) {
                    answer.setIsCorrect(correctAnswersCheckboxes.contains("ch" + i));
                }
                question.setAnswerCount((a.keySet().contains("radios") && (a.get("radios")[0].equals("single"))) ? 1 : 2);
                //answerService.save(answer);
                i++;
            }
        }
        questionService.save(question);
        a.remove("SAVE");
        a.clear();
        a.put("id", new String[]{webRequest.getParameter("id")});
        a.put("questionId", new String[]{webRequest.getParameter("questionId")});
        return new ModelAndView("redirect:/editTest", a);
    }

    @RequestMapping(value = "/newTest", method = RequestMethod.POST)
    public ModelAndView newTest(WebRequest webRequest) {
        Map<String, String[]> map = new TreeMap();
        map.putAll(webRequest.getParameterMap());
        if (map.containsKey("ADDTEST")) {
            Test test = new Test();
            test.setCaption("new test");
            test.setAuthor(userService.findByUsername(RequestUtil.getCurrentUserName()));
            test.setVisible(true);
            testService.save(test);
            map.remove("ADDTEST");
            map.put("id", new String[]{test.getId().toString()});
            return new ModelAndView("redirect:/editTest", map);
        }
        return new ModelAndView("errorpage");
    }

    private boolean isTestAccessibleForUser(Test test) {
        return (test == null) ? false : test.getAuthor().getUsername().equals(RequestUtil.getCurrentUserName());
    }

}
