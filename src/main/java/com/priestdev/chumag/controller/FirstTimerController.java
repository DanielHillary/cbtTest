package com.priestdev.chumag.controller;

import com.priestdev.chumag.entity.FirstTimer;
import com.priestdev.chumag.entity.StandardResponse;
import com.priestdev.chumag.service.FirstTimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/firsttimer")
public class FirstTimerController {

    @Autowired
    private FirstTimerService firstTimerService;

    @PostMapping("/registerfirsttimer")
    public ResponseEntity<StandardResponse> registerFirstTimer(@RequestBody FirstTimer firstTimer){
        return firstTimerService.createFirstTimer(firstTimer);
    }

    @GetMapping("/getallfirsttimers")
    public ResponseEntity<StandardResponse> getAllFirstTimers(){
        return firstTimerService.getAllFirstTimers();
    }

    @GetMapping("/getfirsttimerbyid")
    public ResponseEntity<StandardResponse> getById(@RequestParam("id") Long id){
        return firstTimerService.getFirstTimerById(id);
    }

    @GetMapping("/getfirstimerbyname")
    public ResponseEntity<StandardResponse> getFirstTimerByName(@RequestParam("name") String name){
        return firstTimerService.getFirstTimerByName(name);
    }

    @PutMapping("/updatefirsttimer")
    public ResponseEntity<StandardResponse> updateFirstTimerInfo(@RequestBody FirstTimer firstTimer){
        return firstTimerService.updateFirstTimerInfo(firstTimer);
    }

    @DeleteMapping("/deletefirsttimer")
    public ResponseEntity<StandardResponse> deleteFirstTimer(@RequestParam("id") Long id){
        return firstTimerService.deleteFirstTimer(id);
    }

    @DeleteMapping("/deleteallfirsttimers")
    public ResponseEntity<StandardResponse> deleteAllFirstTimers(){
        return firstTimerService.deleteAllFirstTimers();
    }
}
