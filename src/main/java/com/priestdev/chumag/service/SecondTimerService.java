package com.priestdev.chumag.service;

import com.priestdev.chumag.entity.FirstTimer;
import com.priestdev.chumag.entity.SecondTimer;
import com.priestdev.chumag.entity.StandardResponse;
import com.priestdev.chumag.repository.SecondTimerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecondTimerService {

    @Autowired
    private SecondTimerRepo secondTimerRepo;

    public ResponseEntity<StandardResponse> createSecondTimer(SecondTimer secondTimer) {
        try {
            Optional<SecondTimer> secondTime = secondTimerRepo.findByPhoneNumber(secondTimer.getPhoneNumber());
            if(secondTime.isPresent()){
                return StandardResponse.sendHttpResponse(false, "Second timer already recorded");
            } else return StandardResponse.sendHttpResponse(true, "Successful", secondTimerRepo.save(secondTimer));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create first timer record");
        }
    }

    public ResponseEntity<StandardResponse> getAllSecondTimers() {
        try {
            List<SecondTimer> allSecondTimers = secondTimerRepo.findAll();
            return StandardResponse.sendHttpResponse(true, "Successful", allSecondTimers);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get all first timers");
        }
    }

    public ResponseEntity<StandardResponse> getSecondTimerById(Long id) {
        try {
            SecondTimer secondTimer = secondTimerRepo.findById(id).get();
            return StandardResponse.sendHttpResponse(true, "Successful", secondTimer);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get all first timers");
        }
    }

    public ResponseEntity<StandardResponse> getSecondTimerByName(String name) {
        try {
            List<SecondTimer> allSecondTimers = secondTimerRepo.findAll();
            for(SecondTimer second: allSecondTimers){
                if(second.getFirstName().equalsIgnoreCase(name) || second.getLastName().equalsIgnoreCase(name)){
                    return StandardResponse.sendHttpResponse(true, "Successful", second);
                }
            }
            return StandardResponse.sendHttpResponse(false, "First timer doesn't exit.");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> updateSecondTimerInfo(SecondTimer secondTimer) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", secondTimerRepo.save(secondTimer));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not update info");
        }
    }

    public ResponseEntity<StandardResponse> deleteSecondTimer(Long id) {
        try {
            secondTimerRepo.deleteById(id);
            return StandardResponse.sendHttpResponse(true, "Successfully deleted");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not delete user");
        }
    }

    public ResponseEntity<StandardResponse> deleteAllSecondTimers() {
        try {
            secondTimerRepo.deleteAll();
            return StandardResponse.sendHttpResponse(true, "Successfully deleted");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not delete user");
        }
    }
}