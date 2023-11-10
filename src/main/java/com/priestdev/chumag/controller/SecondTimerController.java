package com.priestdev.chumag.controller;

import com.priestdev.chumag.entity.FirstTimer;
import com.priestdev.chumag.entity.SecondTimer;
import com.priestdev.chumag.entity.StandardResponse;
import com.priestdev.chumag.service.SecondTimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/secondtimer")
public class SecondTimerController {

    @Autowired
    private SecondTimerService secondTimerService;

    @PostMapping("/registersecondtimer")
    public ResponseEntity<StandardResponse> registerSecondTimer(@RequestBody SecondTimer secondTimer){
        return secondTimerService.createSecondTimer(secondTimer);
    }

    @PostMapping("/migratetosecondtimer")
    public ResponseEntity<StandardResponse> registerSecondTimer(@RequestParam("phoneNumber") String phoneNumber,
                                                                @RequestParam("firstName") String firstName){
        return secondTimerService.migrateToSecondTimer(phoneNumber, firstName);
    }

    @GetMapping("/getallsecondtimers")
    public ResponseEntity<StandardResponse> getAllSecondTimers(){
        return secondTimerService.getAllSecondTimers();
    }

    @GetMapping("/getsecondtimerbyid")
    public ResponseEntity<StandardResponse> getById(@RequestParam("id") Long id){
        return secondTimerService.getSecondTimerById(id);
    }

    @GetMapping("/getsecondtimerbyname")
    public ResponseEntity<StandardResponse> getSecondTimerByName(@RequestParam("name") String name){
        return secondTimerService.getSecondTimerByName(name);
    }

    @PutMapping("/updatesecondtimer")
    public ResponseEntity<StandardResponse> updateSecondTimerInfo(@RequestBody SecondTimer secondTimer){
        return secondTimerService.updateSecondTimerInfo(secondTimer);
    }

    @DeleteMapping("/deletefirsttimer")
    public ResponseEntity<StandardResponse> deleteSecondTimer(@RequestParam("id") Long id){
        return secondTimerService.deleteSecondTimer(id);
    }

    @DeleteMapping("/deleteallfirsttimers")
    public ResponseEntity<StandardResponse> deleteAllSecondTimers(){
        return secondTimerService.deleteAllSecondTimers();
    }
}
