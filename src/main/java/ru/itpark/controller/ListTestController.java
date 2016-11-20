package ru.itpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ru.itpark.model.Test;
import ru.itpark.service.TestService;
import ru.itpark.service.UserTestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Kami on 14.11.2016.
 * Отрисовка списков тестов
 */
@Controller
public class ListTestController {

    @Autowired
    private TestService testService;
    @Autowired
    private UserTestService userTestService;

    @RequestMapping(value = "/userTests", method = RequestMethod.POST)
    public ModelAndView getPostUserTests(WebRequest request) {
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
    public ModelAndView getUserTests(WebRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userTests");
        modelAndView.addObject("tests", testService.getTests("Kamila"));
        return modelAndView;
    }

    @RequestMapping(value = "/completedTests", method = RequestMethod.GET)
    public ModelAndView getCompletedTests(WebRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("completedTests");
        modelAndView.addObject("tests", userTestService.getCompletedTestsByUser("Kamila"));
        return modelAndView;
    }

    @RequestMapping(value = "/allTests", method = RequestMethod.GET)
    public ModelAndView getAllTests(WebRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("allTests");
        List<Test> tests = testService.getUnfinishedTests("Kamila");
        String captionFilter = request.getParameter("captionFilter");
        if (captionFilter != null) {
            tests = tests.stream()
                    .filter(test -> test.getCaption().contains(captionFilter))
                    .collect(Collectors.toList());
        }
        modelAndView.addObject("tests", tests);
        return modelAndView;
    }
}
