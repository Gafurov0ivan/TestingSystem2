package ru.itpark.dao;

import ru.itpark.model.Question;

import java.util.List;

/**
 * @author Kamila Iskhakova
 *         Created on 08.11.2016
 */
public interface QuestionDao<E> extends BaseDao<E> {

  List<Question> getQuestions(Long testId);
}
