package com.sds.quizeapp.controller;

import com.sds.quizeapp.dto.QuestionDto;
import com.sds.quizeapp.entity.Quiz;
import com.sds.quizeapp.service.QuizService;
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
    public ResponseEntity<String> createQuiz(@RequestParam String category,
                                       @RequestParam int numQ,
                                       @RequestParam String title) {
        quizService.createQuiz(category, numQ, title);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<QuestionDto>> getQuize(@PathVariable int id) {
        return new ResponseEntity<>(quizService.getQuizQuestionsById(id), HttpStatus.OK);
    }
}
