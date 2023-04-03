package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.Crops;
import com.agrodev.wifarm.entity.MarketCrops;
import com.agrodev.wifarm.entity.Pojo.CropListRequest;
import com.agrodev.wifarm.entity.Pojo.SellCropRequest;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crop")
public class CropController {
    @Autowired
    private CropService cropService;

    @PostMapping("/addcroptofarm")
    public ResponseEntity<StandardResponse> addCrop(@RequestBody Crops crops, @RequestParam("farmId") Long farmId){
        return cropService.addCropToFarm(crops, farmId);
    }
    @PostMapping("/addcropstofarm")
    public ResponseEntity<StandardResponse> addCropsToFarm(@RequestBody CropListRequest request){
        return cropService.addCropsToFarm(request);
    }


    @PostMapping("/addexistingcroptofarm")
    public ResponseEntity<StandardResponse> addExistingCropToFarm(@RequestBody Crops cropName, @RequestParam("farmId") Long farmId){
        return cropService.addExistingCropToFarm(cropName, farmId);
    }

    @PostMapping("/tradecrop")
    public ResponseEntity<StandardResponse> tradeCrop(@RequestBody SellCropRequest request){
        return cropService.tradeCrop(request);
    }

    @GetMapping("/getplantedcrops")
    public ResponseEntity<StandardResponse> getPlantedCrops(@RequestParam("farmId") Long farmId){
        return cropService.getPlantedCrops(farmId);
    }
}
