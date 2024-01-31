package com.jacinthsolutions.cbt.repository;

import com.jacinthsolutions.cbt.entity.CourseTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTestRepository extends JpaRepository<CourseTest, Integer> {
}
