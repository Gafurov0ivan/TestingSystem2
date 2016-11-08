package ru.itpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itpark.dao.TestDao;
import ru.itpark.impl.TestDaoImpl;

/**
 * @author Kamila Iskhakova
 *         Created on 07.11.2016
 */
@Controller
public class MainController {

  @Autowired
  private TestDao testDao;

  @RequestMapping(value="/")
  public String goToHelloPage() {
    //testDao.getTestsByAuthor(1L);
    return "hello";
  }
}
