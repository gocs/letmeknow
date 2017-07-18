package service;

import model.Notification;

import java.util.List;

/**
 * Created by admin on 2017/7/14.
 */
public interface NotificationService {
    public String saveNotification(Notification notification);

    public Notification queryNotification(String id);

    public List<Notification> queryReceivedNotification(String userId);
    }
