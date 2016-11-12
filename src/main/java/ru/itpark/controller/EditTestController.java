package ru.itpark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by maratgumarov on 11.11.16.
 */

@Controller
public class EditTestController {
    @RequestMapping(value = "/newTest")
    public ModelAndView newTest (WebRequest webRequest)
    {
        String description = webRequest.getParameter("radios");
        Map<String , String[]> a =webRequest.getParameterMap();
        for (String s: a.keySet()){
            System.out.println(s);
            String[] strings = a.get(s);
            for (String st:strings) {
                System.out.println("--" + st);
            }
        }
        if (description  != null)
        {
            System.out.println(description);
        }
        else
        {
            System.out.println("NO RADIOS");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("newTest");
        return modelAndView;
    }
}
