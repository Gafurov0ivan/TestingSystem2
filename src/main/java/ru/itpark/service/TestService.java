package ru.itpark.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itpark.dao.TestDao;
import ru.itpark.model.Test;

import java.util.List;

/**
 * @author Kamila Iskhakova
 *         Created on 09.11.2016
 */
@Service
public interface TestService {


  public List<Test> getAll();
}
