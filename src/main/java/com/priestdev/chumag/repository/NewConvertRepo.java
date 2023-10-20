package com.priestdev.chumag.repository;

import com.priestdev.chumag.entity.NewConverts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NewConvertRepo extends JpaRepository<NewConverts, Long> {
    List<NewConverts> findByDateOfConversionBetween(LocalDate startDate, LocalDate endDate);

    List<NewConverts> findByConversionMonth(String month);
}
