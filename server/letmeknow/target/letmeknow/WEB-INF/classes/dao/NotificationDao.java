package dao;

import model.Notification;

import java.util.List;

/**
 * Created by admin on 2017/7/14.
 */
public interface NotificationDao {
    public String insert(Notification notification);
    public Notification find(String id);
    public List<Notification> findReceived(String userId);
    public List<Notification> findSent(String userId);
    }
