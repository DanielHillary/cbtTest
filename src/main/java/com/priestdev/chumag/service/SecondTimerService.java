package com.priestdev.chumag.service;

import com.priestdev.chumag.entity.FirstTimer;
import com.priestdev.chumag.entity.SecondTimer;
import com.priestdev.chumag.entity.StandardResponse;
import com.priestdev.chumag.repository.FirstTimerRepo;
import com.priestdev.chumag.repository.SecondTimerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SecondTimerService {

    @Autowired
    private SecondTimerRepo secondTimerRepo;
    @Autowired
    private FirstTimerRepo firstTimerRepo;

    public ResponseEntity<StandardResponse> createSecondTimer(SecondTimer secondTimer) {
        try {
            Optional<SecondTimer> secondTime = secondTimerRepo.findByPhoneNumber(secondTimer.getPhoneNumber());
            if (secondTime.isPresent()) {
                LocalDate localDate = LocalDate.now();
                secondTimer.setVisitMonth(localDate.getMonthValue());
                return StandardResponse.sendHttpResponse(false, "Second timer already recorded");
            } else
                return StandardResponse.sendHttpResponse(true, "Successful", secondTimerRepo.save(secondTimer));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return StandardResponse.sendHttpResponse(false, "Could not create second timer record");
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
            for (SecondTimer second : allSecondTimers) {
                if (second.getFirstName().equalsIgnoreCase(name) || second.getLastName().equalsIgnoreCase(name)) {
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

    public ResponseEntity<StandardResponse> migrateToSecondTimer(String phoneNumber, String firstName) {
        try {
            Optional<FirstTimer> firstTimer = firstTimerRepo.findByPhoneNumberAndFirstName(phoneNumber, firstName);
            if(firstTimer.isEmpty()){
                return StandardResponse.sendHttpResponse(false,"This person hasn't been to church before. Kindly register as First Timer");
            }else {
                SecondTimer secondTimer = new SecondTimer(firstTimer.get());
                secondTimerRepo.save(secondTimer);
                return StandardResponse.sendHttpResponse(true, "Successfully migrated", secondTimerRepo.save(secondTimer));
            }
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Something went wrong.");
        }
    }
}
