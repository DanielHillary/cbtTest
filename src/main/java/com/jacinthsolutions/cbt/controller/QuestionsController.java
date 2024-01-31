package com.jacinthsolutions.cbt.controller;

import com.jacinthsolutions.cbt.entity.Pojo.QuestionReg;
import com.jacinthsolutions.cbt.entity.Pojo.QuestionRequest;
import com.jacinthsolutions.cbt.entity.Questions;
import com.jacinthsolutions.cbt.entity.StandardResponse;
import com.jacinthsolutions.cbt.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionsController {

    private final QuestionService questionService;

    @PostMapping("/createquestion")
    public ResponseEntity<StandardResponse> createQuestion(@RequestBody QuestionReg questions){
        return questionService.createTestQuestions(questions.getQuestions(), questions.getOptionsList());
    }

    @PutMapping("/updatequestion")
    public ResponseEntity<StandardResponse> updateQuestion(@RequestBody Questions questions){
        return questionService.updateQuestion(questions);
    }

    @GetMapping("gettestquestionforuser")
    public ResponseEntity<StandardResponse> getTestQuestionsForUser(@RequestParam("testId") int testId){
        return questionService.getQuestionsForTest(testId);
    }

    @GetMapping("/getoriginaltestquestions")
    public ResponseEntity<StandardResponse> getOriginalTestQuestions(@RequestParam("testId") int testId,
                                                                     @RequestParam("category") String category,
                                                                     @RequestParam("subject") String subject){
        return questionService.getOriginalTestQuestions(testId, category, subject);
    }

    @GetMapping("/getusertestquestions")
    public ResponseEntity<StandardResponse> getUserTestQuestions(@RequestBody QuestionRequest request){
        return questionService.getUserTestQuestions(request);
    }


    @GetMapping("/getquestion")
    public ResponseEntity<StandardResponse> getQuestion(@RequestParam("id") int id){
        return questionService.getQuestion(id);
    }

    @DeleteMapping("/deletequestion")
    public ResponseEntity<StandardResponse> deleteOriginalQuestion(@RequestParam("id") int id){
        return questionService.deleteQuestion(id);
    }

    @DeleteMapping("/deletealltestquestions")
    public ResponseEntity<StandardResponse> deleteTestQuestions(@RequestParam("testId") Long testId){
        return questionService.deleteTestQuestions(testId);
    }
}
