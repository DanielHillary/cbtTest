package com.jacinthsolutions.cbt.service;

import com.jacinthsolutions.cbt.entity.Options;
import com.jacinthsolutions.cbt.entity.Questions;
import com.jacinthsolutions.cbt.entity.StandardResponse;
import com.jacinthsolutions.cbt.entity.TestResult;
import com.jacinthsolutions.cbt.repository.QuestionsRepository;
import com.jacinthsolutions.cbt.repository.TestResultRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarkingSchemeService {

    private final QuestionsRepository questionsRepository;
    private final TestResultRepo testResultRepo;

    private static final Logger logger = LoggerFactory.getLogger(MarkingSchemeService.class);

    public ResponseEntity<StandardResponse> markScript(Long testId, String userId) {
        try {
            List<Questions> questionsList = questionsRepository.findByTestIdAndIsOriginalAndUserId(testId, false, userId);
            TestResult result = markTestScripts(questionsList);
            return StandardResponse.sendHttpResponse(true, "Successful", result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    private TestResult markTestScripts(List<Questions> questionsList) {
        int rightAnswers = 0;
        int wrongAnswers = 0;
        int totalQuestions = questionsList.size();

        TestResult result = new TestResult();
        Questions sampleQuestion = questionsList.get(0);

        for (Questions questions : questionsList) {
            String[] rightOptionArray = questions.getRightOption().split(", ");
            String[] userChoiceArray = questions.getUsersChoice().split(", ");

            Arrays.sort(rightOptionArray);
            Arrays.sort(userChoiceArray);

            if (Arrays.equals(rightOptionArray, userChoiceArray)) {
                rightAnswers += 1;
                questionsRepository.updateAnsweredCorrectly(true, questions.getId());
            } else {
                questionsRepository.updateAnsweredCorrectly(false, questions.getId());
                wrongAnswers += 1;
            }
        }

        double testScore = (double) rightAnswers / totalQuestions * 100;
        result.setTestScore(testScore);
        result.setRightAnswers(rightAnswers);
        result.setWrongAnswers(wrongAnswers);
        result.setSubject(sampleQuestion.getSubject());
        result.setTestId(sampleQuestion.getTestId());
        result.setTotalQuestions(totalQuestions);
        result.setTestDate(LocalDateTime.now());
        result.setUserId(sampleQuestion.getUserId());

        return testResultRepo.save(result);
    }
}
