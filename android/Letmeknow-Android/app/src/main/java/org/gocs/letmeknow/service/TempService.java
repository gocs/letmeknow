package org.gocs.letmeknow.service;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryEnumerator;
import com.couchbase.lite.QueryRow;
import com.couchbase.lite.View;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.gocs.letmeknow.couchdb.DBWrapper;
import org.gocs.letmeknow.model.Notification;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

import static org.gocs.letmeknow.couchdb.DBWrapper.getCouchDBInstance;

/**
 * Created by dynamicheart on 7/12/2017.
 */

public class TempService {
    public static Observable<List<Notification>> listByGroupId(String groupId){
        return Observable.create(new ObservableOnSubscribe<List<Notification>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Notification>> subscriber) throws Exception {
                Query query = DBWrapper.getCouchDBInstance().getView("group_id_view").createQuery();
                query.setStartKey(groupId);
                query.setEndKey(groupId);
                List<Notification> notifications = new ArrayList<Notification>();
                try{
                    QueryEnumerator resultSet = query.run();
                    ObjectMapper objectMapper = new ObjectMapper();
                    while(resultSet.hasNext()){
                        QueryRow row = resultSet.next();
                        Notification notification = objectMapper.convertValue(row.getDocumentProperties(),Notification.class);
                        notification.setDocument(row.getDocumentProperties());
                        notifications.add(notification);
                    }
                    subscriber.onNext(notifications);
                    subscriber.onComplete();
                }catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}
