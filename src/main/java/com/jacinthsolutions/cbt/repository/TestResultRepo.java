package com.jacinthsolutions.cbt.repository;

import com.jacinthsolutions.cbt.entity.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestResultRepo extends JpaRepository<TestResult, Integer> {
    List<TestResult> findBySubject(String subject);
}
