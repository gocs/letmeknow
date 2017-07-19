package org.gocs.letmeknow.model.component;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dynamicheart on 7/7/2017.
 */

public class Receipt implements Serializable{

    private static final long serialVersionUID = 8641573595948637010L;

    @SerializedName("recipient_name")
    private String recipientName;

    @SerializedName("status")
    private boolean status = false;

    @SerializedName("choice_index")
    private Integer choiceIndex;

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
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
