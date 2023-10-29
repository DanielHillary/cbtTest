package com.priestdev.chumag.repository;

import com.priestdev.chumag.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembersRepository extends JpaRepository<Members, Long> {
    Optional<Members> findByPhoneNumber(String phoneNumber);
}
