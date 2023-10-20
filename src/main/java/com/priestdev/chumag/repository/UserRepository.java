package com.priestdev.chumag.repository;

import com.priestdev.chumag.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String userEmail);

    Optional<User> findByVerificationOtp(String verificationOtp);

    Optional<User> findByUserId(String userId);
}
