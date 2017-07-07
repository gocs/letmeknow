package org.gocs.letmeknow.model.local.sender;

import org.gocs.letmeknow.model.common.BasicNotification;
import org.gocs.letmeknow.model.common.Component.Receipt;
import org.gocs.letmeknow.model.local.NotificationType;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by dynamicheart on 7/7/2017.
 */

public class PersistableSentNotification extends BasicNotification implements Serializable {

    private static final long serialVersionUID = 6733119468871212716L;

    private NotificationType type;

    private Set<Receipt> receipts;

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public Set<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(Set<Receipt> receipts) {
        this.receipts = receipts;
    }
}
