package org.gocs.letmeknow.model.local.recipient;

import org.gocs.letmeknow.model.common.BasicNotification;
import org.gocs.letmeknow.model.common.Component.Receipt;
import org.gocs.letmeknow.model.local.NotificationType;

import java.io.Serializable;

/**
 * Created by dynamicheart on 7/7/2017.
 */

public class PersistableReceivedNotification extends BasicNotification implements Serializable{

    private static final long serialVersionUID = 7032787809002375651L;

    private NotificationType type;

    private Receipt receipt;

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}
