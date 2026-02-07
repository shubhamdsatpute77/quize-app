package com.sds.quizeapp.service;

import com.sds.quizeapp.controller.QuestionController;
import com.sds.quizeapp.dto.QuestionDto;
import com.sds.quizeapp.dto.Response;
import com.sds.quizeapp.entity.Question;
import com.sds.quizeapp.entity.Quiz;
import com.sds.quizeapp.repository.QuestionDao;
import com.sds.quizeapp.repository.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public Integer calculateScore(int id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).orElse(null);
        Map<Integer, Question> questionMap = quiz.getQuestions().stream().collect(Collectors.toMap(Question::getId, question -> question));
        int res = 0;
        for (Response response : responses) {
            if (questionMap.get(response.getId()).getAnswer().equals(response.getResponse())) {
                res++;
            }
        }
        return res;
    }
}
