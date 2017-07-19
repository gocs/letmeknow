package deprecated;

import action.BaseAction;
import couch.Notification;
import couch.NotificationRepo;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/7/19.
 */
public class NotAction extends BaseAction {
    @Autowired
    private NotificationRepo notificationRepo;
    private List<Notification> notifications=new ArrayList<Notification>();

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotificationRepo(NotificationRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }

    @Action(value="/allNot",results={@Result(type="json")})
    public String allNotifications(){
        notifications=notificationRepo.findAll();
        return SUCCESS;
    }
}
