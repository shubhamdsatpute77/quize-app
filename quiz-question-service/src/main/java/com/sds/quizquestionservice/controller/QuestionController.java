package com.sds.quizquestionservice.controller;

import com.sds.quizquestionservice.dto.QuestionDto;
import com.sds.quizquestionservice.dto.Response;
import com.sds.quizquestionservice.entity.Question;
import com.sds.quizquestionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<Question>> getAll() {
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getAllQuestionByCategory(@PathVariable String category) {
        return new ResponseEntity<>(questionService.getAllQuestionByCategory(category), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        questionService.addQuestion(question);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestiionsForQuiz(@RequestParam String category,
                                                              @RequestParam int numQuestions) {
        return new ResponseEntity<>(questionService.getQuestionsForQuiz(category, numQuestions), HttpStatus.OK);
    }

    @PostMapping("all")
    public ResponseEntity<List<QuestionDto>> getAllQuestionsByIds(@RequestBody List<Integer> questionIds) {
        return new ResponseEntity<>(questionService.getQuestionsByIds(questionIds), HttpStatus.OK);
    }

    @PostMapping("score")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<Response> responses) {
        return new ResponseEntity<>(questionService.calculateScore(responses), HttpStatus.OK);
    }
}
