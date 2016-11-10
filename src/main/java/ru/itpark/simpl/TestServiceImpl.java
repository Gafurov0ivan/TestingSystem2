package ru.itpark.simpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itpark.dao.TestDao;
import ru.itpark.model.Test;
import ru.itpark.service.TestService;

import java.util.List;

/**
 * @author Kamila Iskhakova
 *         Created on 10.11.2016
 */
@Service
public class TestServiceImpl implements TestService{

  @Autowired
  private TestDao testDao;

  public List<Test> getAll() {
    return testDao.getAll();
  }
}
