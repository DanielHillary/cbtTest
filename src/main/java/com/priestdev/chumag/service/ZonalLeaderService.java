package com.priestdev.chumag.service;

import com.priestdev.chumag.entity.StandardResponse;
import com.priestdev.chumag.entity.ZonalLeader;
import com.priestdev.chumag.repository.ZonalLeaderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonalLeaderService {

    @Autowired
    private ZonalLeaderRepo zonalLeaderRepo;

    public ResponseEntity<StandardResponse> createZonalLeader(ZonalLeader zonalLeader) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successfully created", zonalLeaderRepo.save(zonalLeader));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create a zonal leader");
        }
    }

    public ResponseEntity<StandardResponse> updateZonalLeader(ZonalLeader zonalLeader) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successfully created", zonalLeaderRepo.save(zonalLeader));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create a zonal leader");
        }
    }

    public ResponseEntity<StandardResponse> getAllZonalLeaders(){
        try {
            List<ZonalLeader> allZonalLeaders = zonalLeaderRepo.findAll();
            return StandardResponse.sendHttpResponse(true, "Successful", allZonalLeaders);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get all zonal leaders");
        }
    }

    public ResponseEntity<StandardResponse> getZonalLeader(Long id){
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", zonalLeaderRepo.findById(id));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get Leader");
        }
    }

    public ResponseEntity<StandardResponse> deleteZonalLeader(Long id){
        try {
            zonalLeaderRepo.deleteById(id);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false,"could not delete leader");
        }
    }

    public ResponseEntity<StandardResponse> deleteAllZonalLeaders(){
        try {
            zonalLeaderRepo.deleteAll();
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false,"could not delete leader");
        }
    }
}
