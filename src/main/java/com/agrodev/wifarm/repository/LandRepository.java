package com.agrodev.wifarm.repository;

import com.agrodev.wifarm.entity.Land;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface LandRepository extends JpaRepository<Land, Long> {
    List<Land> findByLandState(String landState);

    @Transactional
    @Modifying
    @Query(value = "update Land l set l.landUnits = :size where l.landId = :landId")
    void updateLandSize(@Param("size") double size, @Param("landId")Long landId);

    Optional<Land> findByTown(String primaryTownLocation);
}
