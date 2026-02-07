package com.sds.quizeapp.service;

import com.sds.quizeapp.controller.QuestionController;
import com.sds.quizeapp.dto.QuestionDto;
import com.sds.quizeapp.entity.Question;
import com.sds.quizeapp.entity.Quiz;
import com.sds.quizeapp.repository.QuestionDao;
import com.sds.quizeapp.repository.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;
    @Autowired
    private QuestionController questionController;

    public void createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionByCategory(category, numQ) ;
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
    }

    public List<QuestionDto> getQuizQuestionsById(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<QuestionDto> questions = new ArrayList<>();
        if (quiz.isPresent()) {
            quiz.get().getQuestions().stream().forEach(q -> {
                QuestionDto que = new QuestionDto();
                que.setId(q.getId());
                que.setQuestionTitle(q.getQuestionTitle());
                que.setOption1(q.getOption1());
                que.setOption2(q.getOption2());
                que.setOption3(q.getOption3());
                que.setOption4(q.getOption4());
                questions.add(que);
            });
        }
        return questions;
    }
}
