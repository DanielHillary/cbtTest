package com.priestdev.chumag.repository;

import com.priestdev.chumag.entity.ZonalLeaderReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZonalLeaderReportRepo extends JpaRepository<ZonalLeaderReport, Long> {
    List<ZonalLeaderReport> findByReportMonth(int month);
}
