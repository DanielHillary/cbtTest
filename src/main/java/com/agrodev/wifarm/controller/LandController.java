package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.Land;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.service.LandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/land")
public class LandController {
    @Autowired
    private LandService landService;

    @PostMapping("/reigsterexistingland")
    public ResponseEntity<StandardResponse> registerExistingLand(@RequestBody Land land){
        return landService.registerExistingLand(land);
    }

    @GetMapping("/getcroplocation")
    public ResponseEntity<StandardResponse> getCropLocation(@RequestParam("cropName") String cropName){
        return landService.getCultivationLocation(cropName);
    }
    @PostMapping("/registernewland")
    public ResponseEntity<StandardResponse> registerNewLand(@RequestBody Land land){
        return landService.registerNewLand(land);
    }
    @GetMapping("/getalllands")
    public ResponseEntity<StandardResponse> getAllLands(){
        return landService.getAllLands();
    }

    @GetMapping("/getlandbyid")
    public ResponseEntity<StandardResponse> getLandById(@RequestParam("id") Long id){
        return landService.getLandById(id);
    }
    @PutMapping("/updateland")
    public ResponseEntity<StandardResponse> updateLand(@RequestBody Land land){
        return landService.updateLand(land);
    }
    @DeleteMapping("/deleteland")
    public ResponseEntity<StandardResponse> deleteLand(@RequestParam("id") Long id){
        return landService.deleteLand(id);
    }
}
