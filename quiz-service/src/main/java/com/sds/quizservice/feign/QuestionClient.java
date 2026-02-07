package com.sds.quizservice.feign;

import com.sds.quizservice.dto.QuestionDto;
import com.sds.quizservice.dto.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUIZ-QUESTION-SERVICE")
public interface QuestionClient {
    @GetMapping("question/generate")
    ResponseEntity<List<Integer>> getQuestiionsForQuiz(@RequestParam String category,
                                                       @RequestParam int numQuestions);

    @PostMapping("question/all")
    ResponseEntity<List<QuestionDto>> getAllQuestionsByIds(@RequestBody List<Integer> questionIds);

    @PostMapping("question/score")
    ResponseEntity<Integer> calculateScore(@RequestBody List<Response> responses);
}
