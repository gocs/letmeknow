package org.gocs.letmeknow.util.event;

/**
 * Created by dynaicheart on 7/21/2017.
 */

public class LeftChatItemClickEvent {
    private String userId;

    public LeftChatItemClickEvent(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
