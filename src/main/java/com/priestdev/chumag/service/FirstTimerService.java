package com.priestdev.chumag.service;

import com.priestdev.chumag.entity.FirstTimer;
import com.priestdev.chumag.entity.StandardResponse;
import com.priestdev.chumag.repository.FirstTimerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FirstTimerService {

    @Autowired
    private FirstTimerRepo firstTimerRepo;

    public ResponseEntity<StandardResponse> createFirstTimer(FirstTimer firstTimer) {
        try {
            Optional<FirstTimer> firstTimerOptional = firstTimerRepo.findByPhoneNumber(firstTimer.getPhoneNumber());
            if(firstTimerOptional.isPresent()){
                return StandardResponse.sendHttpResponse(false, "First timer already recorded");
            }else {
                return StandardResponse.sendHttpResponse(true, "Successful", firstTimerRepo.save(firstTimer));
            }
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create first timer record");
        }
    }

    public ResponseEntity<StandardResponse> getAllFirstTimers() {
        try {
            List<FirstTimer> allFirstTimers = firstTimerRepo.findAll();
            return StandardResponse.sendHttpResponse(true, "Successful", allFirstTimers);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get all first timers");
        }
    }

    public ResponseEntity<StandardResponse> getFirstTimerById(Long id) {
        try {
            FirstTimer firstTimer = firstTimerRepo.findById(id).get();
            return StandardResponse.sendHttpResponse(true, "Successful", firstTimer);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get all first timers");
        }
    }

    public ResponseEntity<StandardResponse> getFirstTimerByName(String name) {
        try {
            List<FirstTimer> allFirstTimers = firstTimerRepo.findAll();
            for(FirstTimer first : allFirstTimers){
                if(first.getFirstName().equalsIgnoreCase(name) || first.getLastName().equalsIgnoreCase(name)){
                    return StandardResponse.sendHttpResponse(true, "Successful", first);

                }
            }
            return StandardResponse.sendHttpResponse(true, "First timer doesn't exit.");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> updateFirstTimerInfo(FirstTimer firstTimer) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", firstTimerRepo.save(firstTimer));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not update info");
        }
    }

    public ResponseEntity<StandardResponse> deleteFirstTimer(Long id) {
        try {
            firstTimerRepo.deleteById(id);
            return StandardResponse.sendHttpResponse(true, "Successfully deleted");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not delete user");
        }
    }

    public ResponseEntity<StandardResponse> deleteAllFirstTimers() {
        try {
            firstTimerRepo.deleteAll();
            return StandardResponse.sendHttpResponse(true, "Successfully deleted");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not delete user");
        }
    }
}
