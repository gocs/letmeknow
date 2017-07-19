package couch;

import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by admin on 2017/7/19.
 */
public interface NotificationRepo extends CrudRepository<Notification,String> {

    @Query("#{#n1ql.selectEntity} WHERE type = 'notification' ")
    public List<Notification> findAll();

    @Query("#{#n1ql.selectEntity} WHERE type = 'notification' and  sender_id= $1 ")
    public List<Notification> findBySenderId(String senderId);

    @Query("#{#n1ql.selectEntity} WHERE type = 'notification' and  content like $1 ")
    public List<Notification> findByContent(String content);

    @Query("#{#n1ql.selectEntity} WHERE type = 'notification' and  group_id= $1 ")
    public List<Notification>findByGroupId(String groupId);
}
