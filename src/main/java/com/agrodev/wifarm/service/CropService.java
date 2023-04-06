package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.*;
import com.agrodev.wifarm.entity.Pojo.CropListRequest;
import com.agrodev.wifarm.entity.Pojo.PlantCropRequest;
import com.agrodev.wifarm.entity.Pojo.SellCropRequest;
import com.agrodev.wifarm.repository.CropRepository;
import com.agrodev.wifarm.repository.FarmRepository;
import com.agrodev.wifarm.repository.MarketCropsRepo;
import com.agrodev.wifarm.repository.TradeCropsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class
CropService {
    @Autowired
    private CropRepository cropRepository;
    @Autowired
    private FarmRepository farmRepository;
    @Autowired
    private MarketCropsRepo marketCropsRepo;
    @Autowired
    private TradeCropsRepo tradeCropsRepo;

    public ResponseEntity<StandardResponse> addExistingCropToFarm(Crops crops, Long farmId) {
        try {
            Farm farm = farmRepository.findById(farmId).get();
            for(Crops cr : farm.getCropsList()){
                if(cr.getCropName().equalsIgnoreCase(crops.getCropName())){
                    cr.setAmountPlanted(crops.getAmountPlanted());
                    cr.setPrincipalAmount(cr.getPrincipalAmount() + (cr.getAmountPlanted() * cr.getPurchasePrice()));
                    cropRepository.save(cr);
                }
            }
            farmRepository.save(farm);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not add crop to farm land");
        }
    }

    public ResponseEntity<StandardResponse> addCropToFarm(Crops marketCrop, Long farmId){
        try {
            Farm farm = farmRepository.findById(farmId).get();
            farm.getCropsList().add(marketCrop);
            cropRepository.save(marketCrop);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not add crop to farm");
        }
    }

    public ResponseEntity<StandardResponse> addCropsToFarm(CropListRequest requestList){
        try {
            Farm farm = farmRepository.findById(requestList.getFarmId()).get();
            System.out.println("We got here");
            List<MarketCrops> marketCropsList = new ArrayList<>();
            Set<Crops> cropsSet = new HashSet<>();
            for(PlantCropRequest req : requestList.getCropsList()){
                marketCropsList.add(marketCropsRepo.findById(req.getMarketCropId()).get());
            }
            for(PlantCropRequest request : requestList.getCropsList()){
                for(Crops crops : farm.getCropsList()) {
                    if (request.getCropName().equalsIgnoreCase(crops.getCropName())){
                        int planted = crops.getAmountPlanted() + request.getQuantityPlanted();
                        cropRepository.updateQuantityPlanted(planted, crops.getCropId());
                    }
                }
            }
            System.out.println("We got here");
            for(Crops crops : farm.getCropsList()){
                for(MarketCrops marketCrops : marketCropsList){
                    if(marketCrops.getCropName().equalsIgnoreCase(crops.getCropName())){
                        marketCropsList.remove(marketCrops);
                    }
                }
            }
            if(!marketCropsList.isEmpty()) {
                for (MarketCrops crops : marketCropsList) {
                    Crops newCrops = new Crops(crops);
                    newCrops.setFarmId(farm.getFarmId());
                    newCrops.setUserId(farm.getCustomerId());
                    for(PlantCropRequest request : requestList.getCropsList()){
                        if(request.getCropName().equalsIgnoreCase(newCrops.getCropName())){
                            newCrops.setAmountPlanted(request.getQuantityPlanted());
                        }
                    }
                    cropsSet.add(cropRepository.save(newCrops));
                }
                farm.getCropsList().addAll(cropsSet);

                return StandardResponse.sendHttpResponse(true, "Successful",farmRepository.save(farm).getCropsList());
            }else{
                return StandardResponse.sendHttpResponse(true, "Successful");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            return StandardResponse.sendHttpResponse(false, "Could not add crops to farm");
        }
    }

    public ResponseEntity<StandardResponse> tradeCrop(SellCropRequest request) {
        try {
            MarketCrops marketCrops = marketCropsRepo.findByCropName(request.getCropName()).get();
            TradeCrops crops = new TradeCrops();
            crops.setSellingPrice(
                    marketCrops.getCropPrice() + marketCrops.getAccruedAmount()
            );
            crops.setSellerId(request.getSellerId());
            crops.setCropCategory(request.getCropCategory());
            crops.setLGA(request.getLGA());
            crops.setCropName(request.getCropName());
            crops.setCropId(request.getCropId());
            crops.setCropLocation(request.getCropLocation());
            crops.setAccruedAmount(marketCrops.getAccruedAmount());

            tradeCropsRepo.save(crops);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not put crop for trade");
        }
    }

    public ResponseEntity<StandardResponse> getPlantedCrops(Long farmId) {
        try {
            Farm farm = farmRepository.findById(farmId).get();
            List<Crops> cropsList = new ArrayList<>(farm.getCropsList());
            return StandardResponse.sendHttpResponse(true, "Successful", cropsList);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get planted Crops");
        }
    }
}
