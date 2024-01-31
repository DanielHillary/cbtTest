package com.jacinthsolutions.cbt.service;

import com.jacinthsolutions.cbt.entity.Options;
import com.jacinthsolutions.cbt.entity.Questions;
import com.jacinthsolutions.cbt.entity.StandardResponse;
import com.jacinthsolutions.cbt.repository.OptionsRepository;
import com.jacinthsolutions.cbt.repository.QuestionsRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OptionService {

    private final OptionsRepository optionsRepository;
    private final QuestionsRepository questionsRepository;
    private static final Logger logger = LoggerFactory.getLogger(OptionService.class);

    public ResponseEntity<StandardResponse> createOptions(List<Options> optionsList){
        try {
            List<Options> options = optionsRepository.saveAll(optionsList);
            return StandardResponse.sendHttpResponse(true, "Successful", options);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> updateUserChoice(List<Integer> options, int questionId){
        try {
            updateUserMultipleChoices(options, questionId);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public void updateUserChoices(int optionId, int questionId){
        try {
            Questions questions = questionsRepository.findById(questionId).orElseThrow(() -> new NoSuchElementException("Question not found"));
            optionsRepository.updateUserChoice(true, optionId);

            questions.getOptions().forEach((option -> {
                if(option.getId() != optionId){
                    optionsRepository.updateUserChoice(false, option.getId());
                }
            }));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateUserMultipleChoices(List<Integer> choices, int questionId){
        try {
            Questions questions = questionsRepository.findById(questionId).orElseThrow(() -> new NoSuchElementException("Question not found"));
            choices.forEach((ch) -> {
                optionsRepository.updateUserChoice(true, ch);
            });
            questions.getOptions().forEach((option -> {
                choices.forEach((choice) -> {
                    if(option.getId() != choice){
                        optionsRepository.updateUserChoice(false, option.getId());
                    }
                });
            }));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public ResponseEntity<StandardResponse> updateOption(Options option) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", optionsRepository.save(option));
        }catch (Exception e){
            logger.error(e.getMessage());
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> deleteOption(int id) {
        try {
            optionsRepository.deleteById(id);
            return StandardResponse.sendHttpResponse(true, "Successful");
        }catch (Exception e){
            logger.error(e.getMessage());
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }
}
