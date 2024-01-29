package com.jacinthsolutions.cbt.service;

import com.jacinthsolutions.cbt.entity.Notification;
import com.jacinthsolutions.cbt.entity.StandardResponse;
import com.jacinthsolutions.cbt.repository.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepo notificationRepo;
    public ResponseEntity<StandardResponse> getNotificationAboutFarm(String userId) {
        try {
            List<Notification> notificationList = notificationRepo.findByHasRead(false);
            return StandardResponse.sendHttpResponse(true, "Successful", notificationList);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get notifications");
        }
    }

    public ResponseEntity<StandardResponse> updateNotification(Notification notification) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", notificationRepo.save(notification));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not update notification");
        }
    }

    public ResponseEntity<StandardResponse> markAsRead(Long id){
        try {
            Notification note = notificationRepo.findById(id).get();
            note.setHasRead(true);
            notificationRepo.save(note);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not mark notification");
        }
    }
}
