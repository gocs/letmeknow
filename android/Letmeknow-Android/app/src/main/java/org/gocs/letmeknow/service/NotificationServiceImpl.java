package org.gocs.letmeknow.service;

import android.os.IBinder;

import com.couchbase.lite.Database;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.gocs.letmeknow.couchdb.DBWrapper;
import org.gocs.letmeknow.model.Notification;
import org.gocs.letmeknow.util.exception.ConversionExceptipon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/7/12.
 */

public class NotificationServiceImpl implements NotificationService {

    public String create(Notification notification)throws ConversionExceptipon{
        return DBWrapper.create(notification.getDocument());
    }

    public Notification getDocById(String id){
        Object doc = DBWrapper.read(id);
        ObjectMapper mapper = new ObjectMapper();
        Notification notification = mapper.convertValue(doc,Notification.class);
        notification.setDocument((Map<String, Object>)doc);
        return notification;
    }

    public List<Notification> getDocByGroupId(String group_id){
        return null;
    }

    public List<Notification> getDocBySenderId(String sender_id){
        List<Object> docList = DBWrapper.getDocBySenderId(sender_id);
        ObjectMapper mapper = new ObjectMapper();
        List<Notification> notifications = new ArrayList<>();
        for(int i = 0; i < docList.size(); i++){
            Notification notification = mapper.convertValue(docList.get(i),Notification.class);
            notification.setDocument((Map<String,Object>)docList.get(i));
            notifications.add(notification);
        }
        return notifications;
    }

    public List<Notification> getDocByRecipientId(String recipient_id){
        List<Object> docList = DBWrapper.getDocByReceiverId(recipient_id);
        ObjectMapper mapper = new ObjectMapper();
        List<Notification> notifications = new ArrayList<>();
        for(int i = 0; i < docList.size(); i++){
            Notification notification = mapper.convertValue(docList.get(i),Notification.class);
            notification.setDocument((Map<String,Object>)docList.get(i));
            notifications.add(notification);
        }
        return notifications;
    }

    public boolean update(final String key, final Object value, String id){
        return DBWrapper.update(key,value,id);
    }

    public boolean delete(String id){
        return DBWrapper.delete(id);
    }

    public boolean dropDB(){
        Database db = DBWrapper.getCouchDBInstance();
        try{
            db.delete();
        }catch(Exception ignore){

        }
        return true;
    }

}
