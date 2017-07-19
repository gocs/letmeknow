package deprecated;

import java.util.List;

/**
 * Created by admin on 2017/7/14.
 */
public class NotificationServiceImpl implements NotificationService{
    private NotificationDao notificationDao;

    public void setNotificationDao(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    public String saveNotification(Notification notification){
        return notificationDao.insert(notification);
    }

    public Notification queryNotification(String id){
        return notificationDao.find(id);
    }

    public List<Notification> queryReceivedNotification(String userId){
        return notificationDao.findReceived(userId);
    }
}
