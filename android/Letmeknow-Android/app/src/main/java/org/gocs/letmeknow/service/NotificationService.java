package org.gocs.letmeknow.service;

import org.gocs.letmeknow.model.Notification;
import org.gocs.letmeknow.util.exception.ConversionExceptipon;

import java.util.List;
import java.util.Map;

/**
 * Created by dynamicheart on 7/11/2017.
 */

public interface NotificationService {

    /**
     * C-rud
     */
    public String create(Notification notification) throws ConversionExceptipon;

    /**
     * c-R-ud
     */
    public Notification getDocById(String id);
    public List<Notification> getDocByGroupId(String group_id);
    public List<Notification> getDocBySenderId(String sender_id);
    public List<Notification> getDocByRecipientId(String recipient_id);

    /**
     * cr-U-d
     */
    public boolean update(final String key, final Object value, String id);

    /**
     * cru-D
     */
    public boolean delete(String id);
    public boolean dropDB();

}
