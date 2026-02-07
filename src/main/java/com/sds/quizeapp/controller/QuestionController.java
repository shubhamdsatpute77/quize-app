package com.sds.quizeapp.controller;

import com.sds.quizeapp.entity.Question;
import com.sds.quizeapp.service.QuestionService;
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
}
