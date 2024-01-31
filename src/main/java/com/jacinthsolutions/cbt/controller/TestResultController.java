package com.jacinthsolutions.cbt.controller;

import com.jacinthsolutions.cbt.entity.StandardResponse;
import com.jacinthsolutions.cbt.service.TestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/testresults")
@RequiredArgsConstructor
public class TestResultController {

    private final TestResultService testResultService;

    @GetMapping("/getresultsforsubject")
    public ResponseEntity<StandardResponse> getResultsForSubject(@RequestParam("subject") String subject){
        return testResultService.getResultsForSubject(subject);
    }

    @GetMapping("/getresult")
    public ResponseEntity<StandardResponse> getResult(@RequestParam("id") int id){
        return testResultService.getResult(id);
    }

    @GetMapping("/markquiz")
    public ResponseEntity<StandardResponse> scoreQuiz(@RequestParam("tesId") Long testId,
                                                      @RequestParam("userId") String userId){
        return testResultService.markQuiz(testId, userId);
    }
}
