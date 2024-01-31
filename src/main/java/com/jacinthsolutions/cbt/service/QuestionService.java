package com.jacinthsolutions.cbt.service;

import com.jacinthsolutions.cbt.entity.Options;
import com.jacinthsolutions.cbt.entity.Pojo.QuestionRequest;
import com.jacinthsolutions.cbt.entity.Questions;
import com.jacinthsolutions.cbt.entity.StandardResponse;
import com.jacinthsolutions.cbt.entity.User;
import com.jacinthsolutions.cbt.repository.OptionsRepository;
import com.jacinthsolutions.cbt.repository.QuestionsRepository;
import com.jacinthsolutions.cbt.repository.UserRepository;
import com.sendgrid.Response;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionsRepository questionsRepository;
    private final OptionsRepository optionsRepository;
    private final OptionService optionService;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);

    public ResponseEntity<StandardResponse> updateUserChoice(int questionId, List<Integer> choices) {
        try {
            Questions questions = questionsRepository.findById(questionId).orElseThrow(() -> new NoSuchElementException("Question not found"));
            List<String> userChoice = new ArrayList<>();
            choices.forEach((choice) -> {
                questions.getOptions().forEach((option) -> {
                    if (choice == option.getId()) {
                        userChoice.add(option.getOptionAlphabet());
                    }
                });
            });
            String choice = String.join(", ", userChoice);
            questionsRepository.updateUserChoice(choice, questionId);
            optionService.updateUserMultipleChoices(choices, questionId);

            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> getQuestionsForTest(int testId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User loggedUser = userRepository.findByUserName(authentication.getName()).orElseThrow(() -> new NoSuchElementException("User not found"));

            List<Questions> questionsList = questionsRepository.findByTestIdAndIsOriginal(testId, true);
            List<Questions> userQuestionList = new ArrayList<>();

            questionsList.forEach((question -> {
                Set<Options> userOptions = new HashSet<>();
                question.getOptions().forEach((opt) -> {
                    Options options = new Options(opt);
                    options.setOriginal(false);
                    userOptions.add(options);
                });
                optionService.createOptions(userOptions.stream().toList());
                Questions questions = new Questions(question);
                questions.setOriginal(false);
                questions.setUserId(loggedUser.getUserId());
                questions.setOptions(userOptions);
                userQuestionList.add(questions);
                questionsRepository.save(questions);
            }));
            return StandardResponse.sendHttpResponse(true, "Successful", userQuestionList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> createTestQuestions(Questions question, List<Options> optionsList) {
        try {
            Set<Options> options = new HashSet<>();
            for (Options option : optionsList) {
                option.setQuestionId(question.getId());
                option.setOriginal(true);
                options.add(optionsRepository.save(option));
            }
            question.setOriginal(true);
            question.setOptions(options);
            return StandardResponse.sendHttpResponse(true, "Successful", questionsRepository.save(question));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> deleteQuestion(int questionId) {
        try {
            questionsRepository.deleteById(questionId);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> getOriginalTestQuestions(int testId, String category, String subject) {
        try {
            List<Questions> questionsList = questionsRepository.findByTestIdAndIsOriginalAndCategoryAndSubject(testId, true, category, subject);
            return StandardResponse.sendHttpResponse(true, "Successful", questionsList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> getUserTestQuestions(QuestionRequest request) {
        try {
            List<Questions> questionsList = questionsRepository.findByTestIdAndIsOriginalAndCategoryAndSubjectAndUserId(request.getTestId(), false, request.getCategory(), request.getSubject(), request.getUserId());
            return StandardResponse.sendHttpResponse(true, "Successful", questionsList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> getQuestion(int id) {
        try {
            Optional<Questions> questions = questionsRepository.findById(id);
            return questions.map(value -> StandardResponse.sendHttpResponse(true, "Successful", value))
                    .orElseGet(() -> StandardResponse.sendHttpResponse(false, "Question doesn't exit"));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> deleteTestQuestions(Long testId) {
        try {
            List<Questions> questionsList = questionsRepository.findByTestIdAndIsOriginal(testId, true);
            questionsRepository.deleteAll(questionsList);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            logger.error("There was an error. " + e.getMessage());
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> updateQuestion(Questions questions) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", questionsRepository.save(questions));
        } catch (Exception e) {
            logger.error("There was an error. " + e.getMessage());
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }
}
