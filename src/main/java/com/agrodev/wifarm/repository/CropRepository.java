package com.agrodev.wifarm.repository;

import com.agrodev.wifarm.entity.Crops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Supplier;

@Transactional
@Repository
public interface CropRepository extends JpaRepository<Crops, Long> {
    Optional<Crops> findByCropName(String cropName);

    @Transactional
    @Modifying
    @Query(value = "update Crops c set c.amountPlanted = :planted where c.id = :cropId")
    void updateQuantityPlanted(@Param("planted") int planted, @Param("cropId")Long cropId);
}
