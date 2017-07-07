package org.gocs.letmeknow.model.common.Component;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dynamicheart on 7/7/2017.
 */

public class Receipt implements Serializable{

    private static final long serialVersionUID = 4369280252060883662L;

    @SerializedName("recipient_id")
    private String recipientId;

    @SerializedName("status")
    private boolean status;

    @SerializedName("choice_index")
    private Integer choiceIndex;

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getChoiceIndex() {
        return choiceIndex;
    }

    public void setChoiceIndex(Integer choiceIndex) {
        this.choiceIndex = choiceIndex;
    }
}
