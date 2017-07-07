package org.gocs.letmeknow.model.remote;

import com.google.gson.annotations.SerializedName;

import org.gocs.letmeknow.model.common.BasicNotification;
import org.gocs.letmeknow.model.common.Component.Receipt;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by dynamicheart on 7/7/2017.
 */

//only for sync
public class RemoteNotification extends BasicNotification implements Serializable{

    private static final long serialVersionUID = 5244068572994273577L;

    @SerializedName("recipients")
    private Set<Receipt> receipts;

    public Set<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(Set<Receipt> receipts) {
        this.receipts = receipts;
    }
}
