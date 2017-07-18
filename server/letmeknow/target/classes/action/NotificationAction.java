package action;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Notification;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import service.NotificationService;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/7/14.
 */
public class NotificationAction extends BaseAction{
    private NotificationService notificationService;
    private String notificationId;
    private String sender_id;
    private String receiver_id;
    private String content;
    private String resultformat;
    private Integer code=0;
    private String message="you haven't logged in yet";
    private Map<String ,Object> data=new HashMap<String,Object>();

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }

    public void setResultformat(String resultformat) {
        this.resultformat = resultformat;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    @Action(value="/common/saveNotification",results={@Result(type="json")})
    public String saveNotification(){
        Gson gson=new Gson();
        Type type1 = new TypeToken<ArrayList<String>>() {}.getType();
        List<String> receiverList=gson.fromJson(receiver_id,type1);
        Type type2 = new TypeToken<ArrayList<Integer>>() {}.getType();
        List<Integer> resultFormat=gson.fromJson(resultformat,type2);
        Notification notification=new Notification(receiverList,sender_id,content,resultFormat,null);
        notificationService.saveNotification(notification);
        code=1;
        message="success";
        data.put("notification",notification);
        return SUCCESS;
    }

    @Action(value="/common/getNotification",results={@Result(type="json")})
    public String getNotification(){
        Notification notification=notificationService.queryNotification(notificationId);
        code=1;
        message="success";
        data.put("notification",notification);
        return SUCCESS;
    }

    @Action(value="/common/getReceivedNotification",results={@Result(type="json")})
    public String getReceivedNotification(){
        List<Notification> notifications=notificationService.queryReceivedNotification((String)(session().getAttribute("userid")));
        code=1;
        message="success";
        data.put("notification",notifications);
        return SUCCESS;
    }
}