package com.priestdev.chumag.controller;

import com.priestdev.chumag.entity.NewConverts;
import com.priestdev.chumag.entity.StandardResponse;
import com.priestdev.chumag.service.NewConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequestMapping("/api/newconvert")
@RestController
public class NewConvertController {

    @Autowired
    private NewConvertService newConvertService;

    @PostMapping("/addnewconvert")
    public ResponseEntity<StandardResponse> addNewConvert(@RequestBody NewConverts newConverts){
        return newConvertService.addNewConvert(newConverts);
    }

    @GetMapping("/getnewconvert")
    public ResponseEntity<StandardResponse> getNewConvert(@RequestParam("id") Long id){
        return newConvertService.getNewConvert(id);
    }

    @GetMapping("/getallconverts")
    public ResponseEntity<StandardResponse> getAllNewConverts(){
        return newConvertService.getAllNewConverts();
    }

    @GetMapping("/getallconvertsinamonth")
    public ResponseEntity<StandardResponse> getALlNewConvertsPerMonth(@RequestParam("month") String month){
        return newConvertService.getNewConvertsPerMonth(month);
    }

    @GetMapping("/getallconvertsbydatarange")
    public ResponseEntity<StandardResponse> getAllConvertsByDateRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
        return newConvertService.getAllConvertsByDateRange(startDate, endDate);
    }

    @PutMapping("/updatenewconvert")
    public ResponseEntity<StandardResponse> updateNewConvert(@RequestBody NewConverts newConverts){
        return newConvertService.updateNewConvert(newConverts);
    }

    @DeleteMapping("/deletenewconvert")
    public ResponseEntity<StandardResponse> deleteNewConvert(@RequestParam("id") Long id){
        return newConvertService.deleteNewConvert(id);
    }

    @DeleteMapping("/deleteallnewconverts")
    public ResponseEntity<StandardResponse> deleteAllNewConverts(){
        return newConvertService.deleteAllNewConverts();
    }
}
