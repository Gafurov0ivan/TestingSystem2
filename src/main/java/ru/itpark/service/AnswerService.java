package ru.itpark.service;

import org.springframework.stereotype.Service;
import ru.itpark.model.Answer;
import ru.itpark.model.Question;

import java.util.List;

/**
 * Created by maratgumarov on 15.11.16.
 */

@Service
public interface AnswerService {
    List<Answer> getAnswersByQuestionId(Long questionID);
    List<Long> getAnswerIdsByQuestionId(Long questionID);
    Answer getAnswerByAnswerId(Long answerId);
    void save(Answer answer);
    void removeByAnswerId(Long answerId);
    void removeByAnswer(Answer answer);
    void removeAll(List<Long> answeriDs);
}
