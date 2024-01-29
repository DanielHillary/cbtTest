package com.jacinthsolutions.cbt.repository;

import com.jacinthsolutions.cbt.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {
    List<Notification> findByHasRead(boolean b);
}
