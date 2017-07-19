package service;

import couch.Notification;

import java.util.List;

/**
 * Created by admin on 2017/7/19.
 */
public interface NotificationService {
    public List<Notification> searchNotificationBySenderId(String senderId);

    public List<Notification> searchNotificationByContent(String content);

    public List<Notification> searchNotificationByGroupId(String groupId);
    }
