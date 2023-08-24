package com.agrodev.wifarm.repository;

import com.agrodev.wifarm.entity.MarketCrops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Supplier;

@Repository
public interface MarketCropsRepo extends JpaRepository<MarketCrops, Long> {
    Optional<MarketCrops> findByCropName(String cropName);

    @Transactional
    @Modifying
    @Query(value = "update MarketCrops m set m.amountAvailable = :amount where m.id = :cropId")
    void updateCropsAvailable(@Param("amount") double amount, @Param("cropId")Long cropId);
}
