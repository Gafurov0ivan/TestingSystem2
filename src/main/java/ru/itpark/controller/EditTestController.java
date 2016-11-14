package ru.itpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ru.itpark.dao.QuestionDao;
import ru.itpark.model.Question;
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
        Long testId = Long.parseLong(webRequest.getParameter("id"));
        if (testId != null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("editTest");
            modelAndView.addObject("test", testService.getTest(testId));
            return modelAndView;
        }

        Map<String , String[]> a =webRequest.getParameterMap();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editTest");
        //modelAndView.addObject("test", testService.getTest(1L));
        return modelAndView;
    }
}
