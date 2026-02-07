package com.sds.quizeapp.service;

import com.sds.quizeapp.entity.Question;
import com.sds.quizeapp.repository.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getAllQuestionByCategory(String category) {
        return questionDao.findAllByCategory(category);
    }

    public void addQuestion(Question question) {
        questionDao.save(question);
    }
}
