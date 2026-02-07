package com.sds.quizquestionservice.service;

import com.sds.quizquestionservice.dto.QuestionDto;
import com.sds.quizquestionservice.dto.Response;
import com.sds.quizquestionservice.entity.Question;
import com.sds.quizquestionservice.repository.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Integer> getQuestionsForQuiz(String category, int numQuestions) {
        return questionDao.findRandomQuestionByCategory(category, numQuestions);
    }

    public List<QuestionDto> getQuestionsByIds(List<Integer> questionIds) {
        List<QuestionDto> questionDtos = new ArrayList<>();
        for (Integer id : questionIds) {
            Question q = questionDao.findById(id).get();
            QuestionDto que = new QuestionDto();
            que.setId(q.getId());
            que.setQuestionTitle(q.getQuestionTitle());
            que.setOption1(q.getOption1());
            que.setOption2(q.getOption2());
            que.setOption3(q.getOption3());
            que.setOption4(q.getOption4());
            questionDtos.add(que);
        }
        return questionDtos;
    }

    public Integer calculateScore(List<Response> responses) {
        int score = 0;
        for (Response response : responses) {
            Question question = questionDao.findById(response.getId()).get();
            if (question.getAnswer().equals(response.getResponse())) {
                score++;
            }
        }
        return score;
    }
}
