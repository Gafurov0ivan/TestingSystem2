package ru.itpark.service;

import org.springframework.stereotype.Service;
import ru.itpark.model.Test;
import ru.itpark.proxy.UserTestProxy;

import java.util.List;

/**
 * @author Kamila Iskhakova
 *         Created on 09.11.2016
 */
@Service
public interface TestService {


  public List<Test> getAll();

  List<UserTestProxy> getCompletedTestsByUser(String userName);
}
