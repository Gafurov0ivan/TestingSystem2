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
import ru.itpark.util.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.itpark.util.RequestUtil.getId;

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


    @RequestMapping(value = "/userTests2", method = RequestMethod.POST)
    public ModelAndView getPostUserTests2(WebRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userTests");
        Map paramMap = request.getParameterMap();
        if ( paramMap.containsKey("id")) {
            String[] srtIds = (String[]) request.getParameterMap().get("id");
            List<Long> ids = new ArrayList<>(srtIds.length);
            for (String strId : srtIds) {
                ids.add(Long.parseLong(strId));
            }
            if (paramMap.containsKey("makeVisible")) {
                testService.refresh(ids, true);
            } else if (paramMap.containsKey("makeInvisible")) {
                testService.refresh(ids, false);
            }
        }
        modelAndView.addObject("tests", testService.getTests(RequestUtil.getCurrentUserName()));
        return modelAndView;
    }


    @RequestMapping(value = "/userTests", method = RequestMethod.POST)
    public ModelAndView getPostUserTests(WebRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userTests");
        modelAndView.addObject("userName", RequestUtil.getCurrentUserName());
        Long id = getId(request.getParameter("id"));
        if (id != null) {
            testService.refresh(id);
        }
        modelAndView.addObject("tests", testService.getTests(RequestUtil.getCurrentUserName()));
        return modelAndView;
    }

    @RequestMapping(value = {"/", "/userTests"}, method = RequestMethod.GET)
    public ModelAndView getUserTests(WebRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String userName = RequestUtil.getCurrentUserName();
        modelAndView.setViewName("userTests");
        modelAndView.addObject("tests", testService.getTests(RequestUtil.getCurrentUserName()));
        modelAndView.addObject("userName", userName);
        return modelAndView;
    }

    @RequestMapping(value = "/completedTests", method = RequestMethod.GET)
    public ModelAndView getCompletedTests(HttpServletRequest request) {
        String userName = RequestUtil.getCurrentUserName();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("completedTests");
        modelAndView.addObject("tests", userTestService.getCompletedTestsByUser(userName));
        modelAndView.addObject("userName", RequestUtil.getCurrentUserName());
        return modelAndView;
    }

    @RequestMapping(value = "/allTests", method = RequestMethod.GET)
    public ModelAndView getAllTests(WebRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("allTests");
        List<Test> tests = testService.getUnfinishedTests(RequestUtil.getCurrentUserName());
        String captionFilter = request.getParameter("captionFilter");
        if (captionFilter != null) {
            tests = tests.stream()
                    .filter(test -> test.getCaption().contains(captionFilter))
                    .collect(Collectors.toList());

            modelAndView.addObject("captionFilter", captionFilter);
        }
        modelAndView.addObject("userName", RequestUtil.getCurrentUserName());
        modelAndView.addObject("tests", tests);
        return modelAndView;
    }
}
