package action.admin;

import couch.Notification;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import service.NotificationService;

import java.util.List;

import static com.opensymphony.xwork2.Action.SUCCESS;

/**
 * Created by admin on 2017/7/19.
 */
public class NotificationAction {
    @Autowired
    private NotificationService notificationService;
    private List<Notification> notifications;
    private String senderId;
    private String content;
    private String groupId;

    public void setContent(String content) {
        this.content = content;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    @Action(value="/admin/searchNotificationBySenderId",results={@Result(type="json",name=SUCCESS)})
    public String searchNotificationBySenderId(){
        notifications=notificationService.searchNotificationBySenderId(senderId);
        return SUCCESS;
    }

    @Action(value="/admin/searchNotificationByContent",results={@Result(type="json",name=SUCCESS)})
    public String searchNotificationByContent(){
        notifications=notificationService.searchNotificationByContent(content);
        return SUCCESS;
    }

    @Action(value="/admin/searchNotificationByGroupId",results={@Result(type="json",name=SUCCESS)})
    public String searchNotificationByGroupId(){
        notifications=notificationService.searchNotificationByGroupId(groupId);
        return SUCCESS;
    }
// /sender_id
    //content
    //group_id
}
