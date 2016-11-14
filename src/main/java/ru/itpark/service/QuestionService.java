package ru.itpark.service;

import org.springframework.stereotype.Service;
import ru.itpark.model.Question;
import java.util.List;

/**
 * Created by maratgumarov on 14.11.16.
 */

@Service
public interface QuestionService {

    List<Question> getQuestionsByTestId(Long testId);
    Question getQuestionByQuestionId(Long questionId);
    void save(Question question);
    void removeByQuestionId(Question question);
    void removeAll(List<Question> questions);
}
