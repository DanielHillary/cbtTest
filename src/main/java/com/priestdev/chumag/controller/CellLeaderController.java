package com.priestdev.chumag.controller;

import com.priestdev.chumag.entity.CellLeader;
import com.priestdev.chumag.entity.StandardResponse;
import com.priestdev.chumag.service.CellLeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cellleader")
public class CellLeaderController {

    @Autowired
    private CellLeaderService cellLeaderService;

    @PostMapping("/createcellleader")
    public ResponseEntity<StandardResponse> createCellLeader(@RequestBody CellLeader cellLeader){
        return cellLeaderService.createCellLeader(cellLeader);
    }

    @GetMapping("/getcellleader")
    public ResponseEntity<StandardResponse> getCellLeader(@RequestParam("id") Long id){
        return cellLeaderService.getCellLeader(id);
    }

    @GetMapping("/getallcellleaders")
    public ResponseEntity<StandardResponse> getAllCellLeaders(){
        return cellLeaderService.getAllCellLeaders();
    }

    @GetMapping("/getcellleadersbyzone")
    public ResponseEntity<StandardResponse> getCellLeadersByZone(@RequestParam("zone") String zone){
        return cellLeaderService.getCellLeadersByZone(zone);
    }

    @PutMapping("/updatecellleaderinfo")
    public ResponseEntity<StandardResponse> updateCellLeaderInfo(@RequestBody CellLeader cellLeader){
        return cellLeaderService.updateCellLeaderInfo(cellLeader);
    }

    @DeleteMapping("/deletecellleader")
    public ResponseEntity<StandardResponse> deleteCellLeader(@RequestParam("id") Long id){
        return cellLeaderService.deleteCellLeader(id);
    }

    @DeleteMapping("/deleteallcellleaders")
    public ResponseEntity<StandardResponse> deleteAllCellLeaders(){
        return cellLeaderService.deleteAllCellLeaders();
    }
}
