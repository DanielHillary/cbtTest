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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class CropService {
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
                    cr.setPrincipalAmount(cr.getPrincipalAmount() + (cr.getAmountPlanted() * cr.getPrice()));
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

    public ResponseEntity<StandardResponse> addCropsToFarm(CropListRequest cropsLists){
        try {
            Farm farm = farmRepository.findById(cropsLists.getFarmId()).get();
            List<MarketCrops> marketCropsList = new ArrayList<>();
            List<Crops> cropsList = new ArrayList<>();

            for(PlantCropRequest req : cropsLists.getCropsList()){
                marketCropsList.add(marketCropsRepo.findById(req.getMarketCropId()).get());
            }
            for(MarketCrops mark : marketCropsList){
                Crops crops  = new Crops(mark);
                cropsList.add(crops);
            }
            for(Crops cr: cropsList){
                for(Crops cc : farm.getCropsList()) {
                    if (cr.getCropName().equalsIgnoreCase(cc.getCropName())){
                        cc.setAmountPlanted(cc.getAmountPlanted() + cr.getAmountPlanted());
                        cropsList.remove(cr);
                    }
                }
            }
            farm.getCropsList().addAll(cropsList);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
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
