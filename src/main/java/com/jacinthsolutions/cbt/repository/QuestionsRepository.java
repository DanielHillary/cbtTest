package com.jacinthsolutions.cbt.repository;

import com.jacinthsolutions.cbt.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Integer> {
    List<Questions> findByTestId(Long testId);

    @Transactional
    @Modifying
    @Query(value = "update Questions e set e.answeredCorrectly = :correct where e.id = :questionId")
    void updateAnsweredCorrectly(@Param("correct") boolean correct, @Param("questionId") int questionId);

    @Transactional
    @Modifying
    @Query(value = "update Questions e set e.usersChoice = :choice where e.id = :questionId")
    void updateUserChoice(@Param("choice") String choice, @Param("questionId") int questionId);

    List<Questions> findByTestIdAndIsOriginal(long testId, boolean b);

    List<Questions> findByTestIdAndIsOriginalAndUserId(Long testId, boolean b, String userId);

    List<Questions> findByTestIdAndIsOriginalAndCategoryAndSubject(int testId, boolean b, String category, String subject);

    List<Questions> findByTestIdAndIsOriginalAndCategoryAndSubjectAndUserId(int testId, boolean b, String category, String subject, String userId);
}
