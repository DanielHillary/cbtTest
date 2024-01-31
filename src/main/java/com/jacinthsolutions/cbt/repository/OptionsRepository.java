package com.jacinthsolutions.cbt.repository;

import com.jacinthsolutions.cbt.entity.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface OptionsRepository extends JpaRepository<Options, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update Options e set e.isSelected = :choice where e.id = :optionId")
    void updateUserChoice(@Param("choice") boolean choice, @Param("optionId") int optionId);
}
