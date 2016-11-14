package ru.itpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ru.itpark.dao.QuestionDao;
import ru.itpark.model.Question;
import ru.itpark.model.Test;
import ru.itpark.service.QuestionService;
import ru.itpark.service.TestService;

import java.util.Map;

/**
 * Created by maratgumarov on 11.11.16.
 */

@Controller
public class EditTestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/editTest")
    public ModelAndView newTest (WebRequest webRequest)
    {
        String description = webRequest.getParameter("radios");
        Map<String , String[]> a =webRequest.getParameterMap();
        Long testId = null;
        Long questionId = null;
        if (a.containsKey("id")) {
            testId = Long.parseLong(webRequest.getParameter("id"));
        }
        if (a.containsKey("questionId")){
            questionId = Long.parseLong(webRequest.getParameter("questionId"));
        }
        if (testId != null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("editTest");
            Test test = testService.getTest(testId);
            modelAndView.addObject("test", testService.getTest(testId));
            modelAndView.addObject("qId", new Long(questionId == null?1:questionId));
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editTest");
        //modelAndView.addObject("test", testService.getTest(1L));
        return modelAndView;
    }


}
