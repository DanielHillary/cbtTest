package com.jacinthsolutions.cbt.service;

import com.jacinthsolutions.cbt.entity.StandardResponse;
import com.jacinthsolutions.cbt.entity.TestResult;
import com.jacinthsolutions.cbt.repository.TestResultRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestResultService {

    private final TestResultRepo testResultRepo;
    private final MarkingSchemeService schemeService;

    private static final Logger logger = LoggerFactory.getLogger(TestResultService.class);

    public ResponseEntity<StandardResponse> getResultsForSubject(String subject) {
        try {
            List<TestResult> results = testResultRepo.findBySubject(subject);
            return StandardResponse.sendHttpResponse(true, "Successful", results);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> getResult(int id) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", testResultRepo.findById(id));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> markQuiz(long id, String userId) {
        try {
            schemeService.markScript(id, userId);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }
}
