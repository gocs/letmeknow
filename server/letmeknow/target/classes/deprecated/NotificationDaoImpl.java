package deprecated;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by admin on 2017/7/14.
 */
public class NotificationDaoImpl implements NotificationDao {
    private MongoTemplate mongoTemplate;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public String insert(Notification notification) {
        mongoTemplate.save(notification);
        return notification.getId();
    }

    public Notification find(String id) {
        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)), Notification.class);
    }

    public List<Notification> findReceived(String userId) {
       return mongoTemplate.find(new Query(Criteria.where("receiver_id").is(userId)),Notification.class);
    }

    public List<Notification> findSent(String userId){
        return mongoTemplate.find(new Query(Criteria.where("sender)id").is(userId)),Notification.class);
    }
}
