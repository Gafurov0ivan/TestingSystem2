package ru.itpark.simpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.dao.AnswerDao;
import ru.itpark.model.Answer;
import ru.itpark.model.Question;
import ru.itpark.service.AnswerService;

import java.util.List;

/**
 * Created by maratgumarov on 15.11.16.
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerDao answerDao;

    @Autowired
    public AnswerServiceImpl(AnswerDao answerDao) {
        this.answerDao = answerDao;
    }

    @Override
    @Transactional
    public List<Answer> getAnswersByQuestionId(Long questionID) {
        return answerDao.getAnswersByQuestionId(questionID);
    }

    @Override
    public List<Long> getAnswerIdsByQuestionId(Long questionID) {
        return answerDao.getAnswerIdsByQuestionId(questionID);
    }

    @Override
    @Transactional
    public Answer getAnswerByAnswerId(Long answerId) {
        return (Answer)answerDao.find(answerId);
    }

    @Override
    @Transactional
    public void save(Answer answer) {
        answerDao.saveOrUpdate(answer);
    }

    @Override
    public void removeByAnswerId(Long answerId) {
        answerDao.remove(answerId);
    }

    @Override
    @Transactional
    public void removeByAnswer(Answer answer) {
        answerDao.remove(answer);
    }

    @Override
    @Transactional
    public void removeAllByIds(List<Long> answerIds) {
        answerDao.removeAll(answerIds);
    }

    @Override
    @Transactional
    public void removeAllByAnswers(List<Answer> answers) {
        for (Answer answer : answers)
        answerDao.remove(answer);
    }
}
