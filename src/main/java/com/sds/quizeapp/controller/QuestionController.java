package com.sds.quizeapp.controller;

import com.sds.quizeapp.entity.Question;
import com.sds.quizeapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping
    public List<Question> getAll() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public List<Question> getAllQuestionByCategory(@PathVariable String category) {
        return questionService.getAllQuestionByCategory(category);
    }

    @PostMapping
    public String addQuestion(@RequestBody Question question) {
        questionService.addQuestion(question);
        return "Success";
    }
}
