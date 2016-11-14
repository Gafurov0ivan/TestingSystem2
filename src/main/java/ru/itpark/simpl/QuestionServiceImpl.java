package ru.itpark.simpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.dao.QuestionDao;
import ru.itpark.model.Question;
import ru.itpark.service.QuestionService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by maratgumarov on 14.11.16.
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    @Transactional
    public List<Question> getQuestionsByTestId(Long testId) {
        return questionDao.getQuestionsByTestId(testId);
    }

    @Override
    @Transactional
    public Question getQuestionByQuestionId(Long questionId) {
        return (Question) questionDao.find(questionId);
    }

    @Override
    @Transactional
    public void save(Question question) {
        questionDao.saveOrUpdate(question);
    }

    @Override
    @Transactional
    public void removeByQuestionId(Question question) {
        questionDao.remove(question);
    }

    @Override
    @Transactional
    public void removeAll(List<Question> questions) {
        questionDao.removeAll(questions);
    }
}
