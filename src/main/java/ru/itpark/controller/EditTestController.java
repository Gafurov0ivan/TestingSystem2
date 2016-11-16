package ru.itpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ru.itpark.model.Answer;
import ru.itpark.model.Question;
import ru.itpark.model.Test;
import ru.itpark.service.AnswerService;
import ru.itpark.service.QuestionService;
import ru.itpark.service.TestService;
import ru.itpark.service.UserTestService;

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
    UserTestService userTestService;

    @RequestMapping(value = "/editTest")
    public ModelAndView newTest(WebRequest webRequest) {
        String description = webRequest.getParameter("radios");
        Map<String, String[]> a = webRequest.getParameterMap();
        Long testId = null;
        Long questionId = null;
        Question question = null;
        Test test = null;

        //содержится ли номер теста
        if (a.containsKey("id") && a.get("id") != null) {
            test = testService.getTest(Long.parseLong(webRequest.getParameter("id")));
            ModelAndView modelAndView = new ModelAndView();
            //передаем в страницу тест
            modelAndView.addObject("test", test);

            //если есть поле addQuestion (при нажатии на + в верхней панели),
            //добавляем новый вопрос и переходим к его редактированию
            if (a.containsKey("addQuestion")) {

                Long end = new Long(test.getQuestionCount());

                //создали новый пустой вопрос, принадлежащий тесту test
                Question newQuestion = new Question(test);
                test.addQuestion(newQuestion);


                //сохраняем новый вопрос (записываем в БД).
                //количество тестов в вопросе должно увеличиться на 1
                testService.save(test);

                //открываем страницу с новым тестом
                modelAndView.setViewName("editTest");

                //передаем в страницу номер вопроса
                modelAndView.addObject("qId", end);
                return modelAndView;
            } else {

                //Если задан номер вопроса
                if (a.containsKey("questionId") && a.get("questionId") != null) {
                    questionId = Long.parseLong(a.get("questionId")[0]);
                    question = test.getQuestion(questionId.intValue());

                    if (test.getQuestionCount() > 1 && a.containsKey("REMOVE") && !userTestService.isTestFinished(testId)) {
                        Map map = new TreeMap();
                        map.putAll(a);
                        map.remove("REMOVE");
                        test.removeQuestion(question);
                        testService.save(test);
                        if (test.getQuestionCount() == 0) {
                            String[] val = {"0"};
                            map.put("questionId", val);
                        } else {
                            if (questionId >= test.getQuestionCount()) {
                                questionId = new Long(test.getQuestionCount() - 1);
                                String[] val = {questionId.toString()};
                                map.put("questionId", val);
                            }
                        }
                        return new ModelAndView("redirect:/editTest", map);
                    }


                    //Если В запросе eсть поле SAVE запускаем сохранение
                    if (a.containsKey("SAVE")) {

                        //записываем текст вопроса
                        question.setQuestion(a.containsKey("question") ? a.get("question")[0] : "");

                        //deleting old answers from DB

                        question.removeAllAnswers();

                        //Save answers from form
                        List<String> correctAnswersCheckboxes = null;
                        if (a.containsKey("s[]"))
                            correctAnswersCheckboxes = Arrays.asList(a.get("s[]"));

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
                        ModelAndView m = new ModelAndView();
                        m.setViewName("editTest");
                        m.addObject("test", test);
                        m.addObject("qId", questionId);
                        return m;
                    }
                } else {
                    //  Если в тесте нет вопросов
                    if (test.getQuestions() == null || test.getQuestionCount() == 0) {
                        question = new Question();
                        question.setTest(test);
                        test.addQuestion(question);
                        question.setQuestion("");
                        questionService.save(question);
                    }

                    // ссылаемся на первый вопрос из теста
                    ;
                    String[] val = {"0"};
                    Map map = new TreeMap();
                    map.putAll(a);
                    map.put("questionId", val);
                    return new ModelAndView("redirect:/editTest", map);
//                    questionId = 0L;
//                    question = test.getQuestion(questionId.intValue());
                }
                modelAndView.addObject("qId", questionId);
            }
            modelAndView.setViewName("editTest");
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("errorpage");
            return modelAndView;
        }
    }
}