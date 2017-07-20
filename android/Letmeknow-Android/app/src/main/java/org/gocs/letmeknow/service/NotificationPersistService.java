package org.gocs.letmeknow.service;

import com.couchbase.lite.Document;
import com.couchbase.lite.Emitter;
import com.couchbase.lite.Mapper;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryEnumerator;
import com.couchbase.lite.QueryRow;
import com.couchbase.lite.Reducer;
import com.couchbase.lite.View;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.gocs.letmeknow.model.Notification;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

import static org.gocs.letmeknow.util.manager.couchbase.DataBaseClient.getCouchDBInstance;

/**
 * Created by dynamicheart on 7/12/2017.
 */

public class NotificationPersistService {
    public static Observable<String> create(Notification notification) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> subscriber) throws Exception {
                Document doc = getCouchDBInstance().createDocument();
                try {
                    notification.setType("notification");

                    //set up channels
                    List<String> channels = new LinkedList<String>();
                    channels.add(notification.getSenderId());
                    for(String recipientId:notification.getReceiptMap().keySet()){
                        channels.add(recipientId);
                    }

                    Map<String, Object> properties = new ObjectMapper().convertValue(notification, new TypeReference<Map<String, Object>>() {});
                    Document document = getCouchDBInstance().createDocument();
                    document.putProperties(properties);
                    subscriber.onNext(document.getId());
                    subscriber.onComplete();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    public static Observable<Notification> findOne(String id){
        return Observable.create(new ObservableOnSubscribe<Notification>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Notification> subscriber) throws Exception {
                try{
                    Document doc = getCouchDBInstance().getDocument(id);
                    Notification notification = new ObjectMapper().convertValue(doc.getProperties(),Notification.class);
                    notification.setDocument(doc.getProperties());
                    subscriber.onNext(notification);
                    subscriber.onComplete();
                }catch (Exception e){
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    public static Observable<List<Notification>> listByGroupId(String groupId){
        return Observable.create(new ObservableOnSubscribe<List<Notification>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Notification>> subscriber) throws Exception {
                Query query = NotificationViewUtils.getGroupIdView().createQuery();
                query.setStartKey(groupId);
                query.setEndKey(groupId);
                List<Notification> notifications = new ArrayList<Notification>();
                try{
                    QueryEnumerator resultSet = query.run();
                    ObjectMapper objectMapper = new ObjectMapper();
                    while(resultSet.hasNext()){
                        QueryRow row = resultSet.next();
                        Notification notification = objectMapper.convertValue(row.getValue(),Notification.class);
                        notification.setDocument(row.getDocumentProperties());
                        notifications.add(notification);
                    }
                    subscriber.onNext(notifications);
                    subscriber.onComplete();
                }catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    public static Observable<List<Notification>> listBySenderId(String senderId){
        return Observable.create(new ObservableOnSubscribe<List<Notification>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Notification>> subscriber) throws Exception {
                Query query = NotificationViewUtils.getSenderIdView().createQuery();
                query.setStartKey(senderId);
                query.setEndKey(senderId);
                List<Notification> notifications = new ArrayList<Notification>();
                try{
                    QueryEnumerator resultSet = query.run();
                    ObjectMapper objectMapper = new ObjectMapper();
                    while(resultSet.hasNext()){
                        QueryRow row = resultSet.next();
                        Notification notification = objectMapper.convertValue(row.getValue(),Notification.class);
                        notification.setDocument(row.getDocumentProperties());
                        notifications.add(notification);
                    }
                    subscriber.onNext(notifications);
                    subscriber.onComplete();
                }catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    public static Observable<List<Notification>> listByRecipientId(String recipientId){
        return Observable.create(new ObservableOnSubscribe<List<Notification>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Notification>> subscriber) throws Exception {
                Query query = NotificationViewUtils.getRecipientIdView().createQuery();
                query.setStartKey(recipientId);
                query.setEndKey(recipientId);
                List<Notification> notifications = new ArrayList<Notification>();
                try{
                    QueryEnumerator resultSet = query.run();
                    ObjectMapper objectMapper = new ObjectMapper();
                    while(resultSet.hasNext()){
                        QueryRow row = resultSet.next();
                        Notification notification = objectMapper.convertValue(row.getValue(),Notification.class);
                        notification.setDocument(row.getDocumentProperties());
                        notifications.add(notification);
                    }
                    subscriber.onNext(notifications);
                    subscriber.onComplete();
                }catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }

    private static class NotificationViewUtils{
        private static final String TYPE_KEY = "type";
        private static final String TYPE_NOTIFICATION = "notification";

        private static final String SENDER_KEY = "sender_id";
        private static final String GROUP_KEY = "group_id";
        private static final String RECEIPTS_KEY = "receipts";
        private static final String RECIPIENT_KEY = "recipient";
        private static final String ID_KEY = "_id";

        private static final String SENDER_ID_VIEW = "sender_id_view";
        private static final String GROUP_ID_VIEW = "group_id_view";
        private static final String RECIPIENT_ID_VIEW = "recipient_id_view";
        private static final String AGGREGATE_VIEW = "aggregate_view";

        static View getGroupIdView() {
            View view = getCouchDBInstance().getView(GROUP_ID_VIEW);
            if(view.getMap() == null){
                Mapper mapper = new Mapper(){
                    public void map(Map<String, Object> document, Emitter emitter) {
                        String type = (String)document.get(TYPE_KEY);
                        if (TYPE_NOTIFICATION.equals(type))
                            emitter.emit(document.get(GROUP_KEY), document);
                    }
                };
                view.setMap(mapper, "1.0");
            }
            return view;
        }

         static View getSenderIdView() {
            View view = getCouchDBInstance().getView(SENDER_ID_VIEW);
            if(view.getMap() == null){
                Mapper mapper = new Mapper(){
                    public void map(Map<String, Object> document, Emitter emitter) {
                        String type = (String)document.get(TYPE_KEY);
                        if (TYPE_NOTIFICATION.equals(type))
                            emitter.emit(document.get(SENDER_KEY), document);
                    }
                };
                view.setMap(mapper, "1.0");
            }
            return view;
        }

        @SuppressWarnings("unchecked")
        static View getRecipientIdView() {
            View view = getCouchDBInstance().getView(RECIPIENT_ID_VIEW);
            if(view.getMap() == null){
                Mapper mapper = new Mapper(){
                    public void map(Map<String, Object> document, Emitter emitter) {
                        String type = (String)document.get(TYPE_KEY);
                        if (TYPE_NOTIFICATION.equals(type)){
                            Map<String,Object> receipts = (Map<String,Object>)document.get(RECEIPTS_KEY);
                            for(String recipientId:receipts.keySet()){
                                emitter.emit(recipientId,document);
                            }
                        }
                    }
                };
                view.setMap(mapper, "1.0");
            }
            return view;
        }

        static View getAggregateView() {
            View view = getCouchDBInstance().getView(AGGREGATE_VIEW);
            if(view.getReduce() == null){
                Mapper mapper = new Mapper(){
                    public void map(Map<String, Object> document, Emitter emitter) {
                        String type = (String)document.get(TYPE_KEY);
                        if (TYPE_NOTIFICATION.equals(type)){
                            emitter.emit(document.get(ID_KEY),null);
                        }
                    }
                };

                Reducer reducer = new Reducer() {
                    @Override
                    public Object reduce(List<Object> keys, List<Object> values, boolean rereduce) {

                        return values.size();
                    }
                };
                view.setMapReduce(mapper,reducer,"1.0");
            }
            return view;
        }
    }
}
