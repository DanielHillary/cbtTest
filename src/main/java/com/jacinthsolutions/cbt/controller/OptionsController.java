package com.jacinthsolutions.cbt.controller;

import com.jacinthsolutions.cbt.entity.Options;
import com.jacinthsolutions.cbt.entity.StandardResponse;
import com.jacinthsolutions.cbt.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/options")
@RequiredArgsConstructor
public class OptionsController {

    private final OptionService optionService;

    @PutMapping("/updateoption")
    public ResponseEntity<StandardResponse> updateOption(@RequestBody Options option){
        return optionService.updateOption(option);
    }

    @DeleteMapping("/deleteoption")
    public ResponseEntity<StandardResponse> deleteOption(@RequestParam("id") int id){
        return optionService.deleteOption(id);
    }
}
