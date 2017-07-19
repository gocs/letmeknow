package service.impl;

import couch.Notification;
import couch.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.NotificationService;

import java.util.List;

/**
 * Created by admin on 2017/7/19.
 */
@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepo notificationRepo;

    public void setNotificationRepo(NotificationRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }

    public List<Notification> searchNotificationBySenderId(String senderId){
        return notificationRepo.findBySenderId(senderId);
    }

    public List<Notification> searchNotificationByContent(String content){
        content="%"+content+"%";
        return notificationRepo.findByContent(content);
    }

    public List<Notification> searchNotificationByGroupId(String groupId){
        return notificationRepo.findByGroupId(groupId);
    }
}
