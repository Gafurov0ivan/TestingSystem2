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
    User user;
    Test test;
    Question question;
    Integer qid;

   @RequestMapping(value = "/editTest", params = {"id"})
    public ModelAndView editTestWithoutQuestionId(WebRequest webRequest) {
        Map<String, String[]> map = new TreeMap();
        map.putAll(webRequest.getParameterMap());
        map.put("questionId", new String[]{"0"});
        return new ModelAndView("redirect:/editTest", map);
    }

    @RequestMapping(value = "/editTest", params = {"id", "questionId"})
    public ModelAndView editTest(WebRequest webRequest) {
        //Временно. Юзаем все запросы для юзера номер 1
        user = userService.getUserById(1L);
        test = testService.getTest(Long.parseLong(webRequest.getParameter("id")));
        qid = Integer.parseInt(webRequest.getParameter("questionId"));
        question = test.getQuestion(qid);
        if (qid < 0 || qid > test.getQuestionCount()) {
            return new ModelAndView("errorpage");
        }

        if (userService.canEditTest(user, test)) {
            modelAndView = new ModelAndView();
            modelAndView.addObject("test", test);
            if (webRequest.getParameterMap().containsKey("addQuestion")) {
                Long end = new Long(test.getQuestionCount());
                Question newQuestion = new Question(test);
                test.addQuestion(newQuestion);
                testService.save(test);
                modelAndView.setViewName("editTest");
                modelAndView.addObject("qId", end);
                return modelAndView;
            }
            modelAndView.addObject("qId", new Long(qid));
            return modelAndView;
        }
        return new ModelAndView("errorpage");
    }

    @RequestMapping(value = "/editTest", params = {"id", "questionId", "REMOVE"})
    public ModelAndView removeQuestion(WebRequest webRequest) {
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

        Map<String, String[]> map = new TreeMap();
        map.putAll(webRequest.getParameterMap());

        Test test = testService.getTest(Long.parseLong(webRequest.getParameter("id")));
        test.setCaption(map.get("saveTestCaption")[0]);
        map.remove("saveTestCaption");
        testService.save(test);

        return new ModelAndView("redirect:/editTest", map);
    }

    @RequestMapping(value = "/editTest", params = {"id", "questionId", "SAVE"})
    public ModelAndView saveQuestion(WebRequest webRequest){
        Map<String, String[]> a = new TreeMap();
        a.putAll(webRequest.getParameterMap());

        //такой метод не работает
        //Question question = test.getQuestion(qid);

        //а такой работает. надо разобраться почему
        Question question = testService.getTest(test.getId()).getQuestion(qid);

        question.setQuestion(a.get("question")[0]);

        //deleting old answers from DB
        question.removeAllAnswers();

        //Save answers from form
        List<String> correctAnswersCheckboxes = null;
        if (a.containsKey("s[]"))
            correctAnswersCheckboxes = Arrays.asList(a.get("s[]"));

        List<Answer> answers= answerService.getAnswersByQuestionId(question.getId());
        int i = 0;
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
        return new ModelAndView("redirect:/editTest", a);
    }
}
