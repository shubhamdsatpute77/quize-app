package com.sds.quizservice.service;

import com.sds.quizservice.dto.QuestionDto;
import com.sds.quizservice.dto.QuizDto;
import com.sds.quizservice.dto.Response;
import com.sds.quizservice.entity.Quiz;
import com.sds.quizservice.feign.QuestionClient;
import com.sds.quizservice.repository.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionClient questionClient;

    public void createQuiz(QuizDto quizDto) {
        List<Integer> questions = questionClient.getQuestiionsForQuiz(quizDto.getCategory(), quizDto.getQuestions()).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(quizDto.getTitle());
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);
    }

    public List<QuestionDto> getQuizQuestionsById(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        return questionClient.getAllQuestionsByIds(quiz.get().getQuestionIds()).getBody();
    }

    public Integer calculateScore(int id, List<Response> responses) {
        return questionClient.calculateScore(responses).getBody();
    }
}
