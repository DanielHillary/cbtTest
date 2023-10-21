package com.priestdev.chumag.repository;

import com.priestdev.chumag.entity.CellLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CellLeaderRepo extends JpaRepository<CellLeader, Long> {
    Optional<CellLeader> findByEmail(String email);

    Optional<CellLeader> findByPhoneNumber(String phoneNumber);

    List<CellLeader> findByZoneName(String zone);

    List<CellLeader> findByZoneId(Long zoneId);
}
