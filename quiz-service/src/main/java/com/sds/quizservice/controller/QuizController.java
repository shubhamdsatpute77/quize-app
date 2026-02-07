package com.sds.quizservice.controller;

import com.sds.quizservice.dto.QuestionDto;
import com.sds.quizservice.dto.QuizDto;
import com.sds.quizservice.dto.Response;
import com.sds.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
        quizService.createQuiz(quizDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("questions/{id}")
    public ResponseEntity<List<QuestionDto>> getQuizQuestions(@PathVariable int id) {
        return new ResponseEntity<>(quizService.getQuizQuestionsById(id), HttpStatus.OK);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> responses) {
        return new ResponseEntity<>(quizService.calculateScore(id, responses), HttpStatus.OK);
    }
}
