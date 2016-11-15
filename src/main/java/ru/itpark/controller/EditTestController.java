package ru.itpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ru.itpark.dao.QuestionDao;
import ru.itpark.model.Answer;
import ru.itpark.model.Question;
import ru.itpark.model.Test;
import ru.itpark.service.AnswerService;
import ru.itpark.service.QuestionService;
import ru.itpark.service.TestService;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

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

    @RequestMapping(value = "/editTest")
    public ModelAndView newTest(WebRequest webRequest) {
        String description = webRequest.getParameter("radios");
        Map<String, String[]> a = webRequest.getParameterMap();
        Long testId = null;
        Long questionId = null;
        Question question = null;


        if (a.containsKey("id")) {
            testId = Long.parseLong(webRequest.getParameter("id"));
            //  Adding new question to test
            if (a.containsKey("addQuestion")) {
                questionService.save(new Question(testService.getTest(testId)));
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("editTest");
                modelAndView.addObject("test", testService.getTest(testId));
                return modelAndView;
            }
        }

        if (a.containsKey("questionId")) {
            questionId = Long.parseLong(webRequest.getParameter("questionId"));
            question = testService.getTest(testId).getQuestion(questionId.intValue());
            //Saving current question
            if (a.containsKey("SAVE")) {
                question.setQuestion(a.containsKey("question")? a.get("question")[0] : "");
                //deleting old answers from DB
                List<Long> answers = answerService.getAnswerIdsByQuestionId(questionId);
                answerService.removeAll(answers);

                //Save answers from form

                List<String> correctAnswersCheckboxes = null;
                if (a.containsKey("s[]")) {
                    correctAnswersCheckboxes = Arrays.asList(a.get("s[]"));
                    int i = 1;
                    for (String key : a.keySet()) {
                        if (key.matches("field\\[\\d\\d*\\]")) {
                            Answer answer = new Answer();
                            answer.setQuestion(question);
                            answer.setText(a.get(key)[0]);
                            if (correctAnswersCheckboxes != null) {
                                answer.setIsCorrect(correctAnswersCheckboxes.contains("ch" + i));
                            }
                            answerService.save(answer);
                            i++;
                        }

                    }
                }
                questionService.save(question);
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        if (testId != null) {
            modelAndView.setViewName("editTest");
            modelAndView.addObject("test", testService.getTest(testId));
            if (testService.getTest(testId).getQuestions() == null){
                question = new Question();
                question.setTest(testService.getTest(testId));
                question.setQuestion("");
                questionService.save(question);
            } else {
                if (question == null)
                for (Question q:testService.getTest(testId).getQuestions())
                {
                    modelAndView.addObject("qId",q.getId());
                    return modelAndView;
                }
            }
            modelAndView.addObject("qId", question.getId());
            return modelAndView;
        }
        modelAndView.setViewName("editTest");
        //modelAndView.addObject("test", testService.getTest(1L));
        return modelAndView;
    }
}

