package com.priestdev.chumag.service;

import com.priestdev.chumag.entity.CellLeader;
import com.priestdev.chumag.entity.StandardResponse;
import com.priestdev.chumag.repository.CellLeaderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CellLeaderService {

    @Autowired
    private CellLeaderRepo cellLeaderRepo;

    public ResponseEntity<StandardResponse> createCellLeader(CellLeader cellLeader) {
        try {
            CellLeader leader;
            Optional<CellLeader> presentCell = cellLeaderRepo.findByPhoneNumber(cellLeader.getPhoneNumber());
            if(presentCell.isPresent()){
                return StandardResponse.sendHttpResponse(false, "Cell leader already already exists");
            }else {
               leader = cellLeaderRepo.save(cellLeader);
                return StandardResponse.sendHttpResponse(true, "Successful", leader );
            }

        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create cell leader");
        }
    }

    public ResponseEntity<StandardResponse> getAllCellLeaders(){
        try {
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "There was an error somewhere.");
        }
    }

    public ResponseEntity<StandardResponse> getCellLeader(Long id){
        try {

            return StandardResponse.sendHttpResponse(true, "Successful", cellLeaderRepo.findById(id));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> updateCellLeaderInfo(CellLeader cellLeader){
        try {

            return StandardResponse.sendHttpResponse(true, "Successfully updated", cellLeaderRepo.save(cellLeader));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> deleteCellLeader(Long id){
        try {
            cellLeaderRepo.deleteById(id);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception exception) {
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> deleteAllCellLeaders(){
        try {
            cellLeaderRepo.deleteAll();
            return StandardResponse.sendHttpResponse(true,"Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> getCellLeadersByZone(Long zoneId) {
        try {
            List<CellLeader> cellLeaders = cellLeaderRepo.findByZoneId(zoneId);
            return StandardResponse.sendHttpResponse(true, "Successful", cellLeaders);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get cell leaders by zone");
        }
    }
}
