package org.gocs.letmeknow.util.event;

/**
 * Created by dynamicheart on 7/20/2017.
 */

public class UserLoginEvent {
    private LoginType loginType;

    public UserLoginEvent(LoginType loginType) {
        this.loginType = loginType;
    }

    public enum LoginType{
        LOGIN,REGISTER
    }

    public LoginType getLoginType() {
        return loginType;
    }
}
