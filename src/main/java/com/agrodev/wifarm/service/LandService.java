package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.CropLocation;
import com.agrodev.wifarm.entity.Land;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.repository.CropLocationRepo;
import com.agrodev.wifarm.repository.LandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LandService {
    @Autowired
    private LandRepository landRepository;
    @Autowired
    private CropLocationRepo cropLocationRepo;

    public ResponseEntity<StandardResponse> registerExistingLand(Land land) {
        try {
            List<Land> landList = landRepository.findByLandState(land.getLandState());
            for(Land l : landList){
                if(l.getTown().equalsIgnoreCase(land.getTown())){
                    l.setLandUnits(l.getLandUnits() + land.getLandUnits());
                    landRepository.save(l);
                }
            }
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not add to existing lands");
        }
    }

    public ResponseEntity<StandardResponse> getCropCultivationLocation(List<String> cropNames) {
        try {
            List<String> locations = new ArrayList<>();
            return StandardResponse.sendHttpResponse(true, "Successful", locations);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get land locations");
        }
    }

    public ResponseEntity<StandardResponse> getCultivationLocation(String cropName){
        try {
            List<String> locations = new ArrayList<>();
            List<CropLocation> locationList = cropLocationRepo.findByCropName(cropName);
            for(CropLocation cr : locationList){
                locations.add(cr.getCropLocation());
            }
            return StandardResponse.sendHttpResponse(true, "Successful", locations);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get land location");
        }
    }

    public ResponseEntity<StandardResponse> registerNewLand(Land land) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", landRepository.save(land));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not register new land");
        }
    }

    public ResponseEntity<StandardResponse> getAllLands() {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", landRepository.findAll());
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get all lands");
        }
    }

    public ResponseEntity<StandardResponse> updateLand(Land land) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", landRepository.save(land));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not update land");
        }
    }

    public ResponseEntity<StandardResponse> deleteLand(Long id) {
        try {
            landRepository.deleteById(id);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not delete land");
        }
    }

    public ResponseEntity<StandardResponse> getLandById(Long id) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", landRepository.findById(id));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get particular land");
        }
    }
}
