package com.priestdev.chumag.service;

import com.priestdev.chumag.entity.NewConverts;
import com.priestdev.chumag.entity.StandardResponse;
import com.priestdev.chumag.repository.NewConvertRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NewConvertService {
    @Autowired
    private NewConvertRepo newConvertRepo;

    public ResponseEntity<StandardResponse> addNewConvert(NewConverts newConverts) {
        try {
            String month = newConverts.getDateOfConversion().getMonth().toString();
            newConverts.setConversionMonth(month);

            return StandardResponse.sendHttpResponse(true, "Successful", newConvertRepo.save(newConverts));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not add new convert");
        }
    }

    public ResponseEntity<StandardResponse> getNewConvert(Long id) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", newConvertRepo.findById(id));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not retrieve new convert");
        }
    }


    public ResponseEntity<StandardResponse> getAllNewConverts() {
        try {
            List<NewConverts> convertsList = newConvertRepo.findAll();
            return StandardResponse.sendHttpResponse(true, "Successful", convertsList);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not retrieve all converts");
        }
    }

    public ResponseEntity<StandardResponse> getAllConvertsByDateRange(LocalDate startDate, LocalDate endDate){
        try {
            List<NewConverts> convertsList = newConvertRepo.findByDateOfConversionBetween(startDate, endDate);
            return StandardResponse.sendHttpResponse(true, "Successful", convertsList);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not retrieve new converts");
        }
    }

    public ResponseEntity<StandardResponse> getNewConvertsPerMonth(String month) {
        try {
            List<NewConverts> converts = newConvertRepo.findByConversionMonth(month);
            return StandardResponse.sendHttpResponse(true, "Successful", converts);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not retrieve converts");
        }
    }

    public ResponseEntity<StandardResponse> updateNewConvert(NewConverts newConverts) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", newConvertRepo.save(newConverts));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get converts");
        }
    }

    public ResponseEntity<StandardResponse> deleteNewConvert(Long id) {
        try {
            newConvertRepo.deleteById(id);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not delete new convert");
        }
    }

    public ResponseEntity<StandardResponse> deleteAllNewConverts() {
        try {
            newConvertRepo.deleteAll();
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not delete all converts");
        }
    }
}
