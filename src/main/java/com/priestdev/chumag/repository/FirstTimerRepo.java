package com.priestdev.chumag.repository;

import com.priestdev.chumag.entity.FirstTimer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FirstTimerRepo extends JpaRepository<FirstTimer, Long> {
    Optional<FirstTimer> findByPhoneNumber(String phoneNumber);

    Optional<FirstTimer> findByPhoneNumberAndFirstName(String phoneNumber, String firstName);

    List<FirstTimer> findByVisitMonth(int monthInt);
}
