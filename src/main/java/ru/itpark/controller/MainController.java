package ru.itpark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kamila Iskhakova
 *         Created on 07.11.2016
 */
@Controller
public class MainController {


  @RequestMapping(value="/")
  public String goToHelloPage() {
    return "hello";
  }
}
