package com.priestdev.chumag.repository;

import com.priestdev.chumag.entity.ZonalLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonalLeaderRepo extends JpaRepository<ZonalLeader, Long> {
}
