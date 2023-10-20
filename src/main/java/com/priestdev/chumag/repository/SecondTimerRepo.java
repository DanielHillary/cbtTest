package com.priestdev.chumag.repository;

import com.priestdev.chumag.entity.SecondTimer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecondTimerRepo extends JpaRepository<SecondTimer, Long> {
    Optional<SecondTimer> findByPhoneNumber(String phoneNumber);
}
