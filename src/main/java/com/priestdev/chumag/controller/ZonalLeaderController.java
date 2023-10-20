package com.priestdev.chumag.controller;

import com.priestdev.chumag.entity.StandardResponse;
import com.priestdev.chumag.entity.ZonalLeader;
import com.priestdev.chumag.service.ZonalLeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/zonalleader/")
public class ZonalLeaderController {

    @Autowired
    private ZonalLeaderService zonalLeaderService;

    @PostMapping("/createnewzonalleader")
    public ResponseEntity<StandardResponse> createNewZonalLeader(@RequestBody ZonalLeader zonalLeader){
        return zonalLeaderService.createZonalLeader(zonalLeader);
    }

    @GetMapping("/getzonalleader")
    public ResponseEntity<StandardResponse> getZonalLeader(@RequestParam("id") Long id){
        return zonalLeaderService.getZonalLeader(id);
    }

    @GetMapping("/getallzonalleaders")
    public ResponseEntity<StandardResponse> getAllZonalLeaders(){
        return zonalLeaderService.getAllZonalLeaders();
    }

    @PutMapping("/updatezonalleaderinfo")
    public ResponseEntity<StandardResponse> updateNewZonalLeader(@RequestBody ZonalLeader zonalLeader){
        return zonalLeaderService.updateZonalLeader(zonalLeader);
    }

    @DeleteMapping("/deletezonalleader")
    public ResponseEntity<StandardResponse> deleteZonalLeader(@RequestParam("id") Long id){
        return zonalLeaderService.deleteZonalLeader(id);
    }

    @DeleteMapping("/deleteallzonalleaders")
    public ResponseEntity<StandardResponse> deleteAllZonalLeaders(){
        return zonalLeaderService.deleteAllZonalLeaders();
    }

}
