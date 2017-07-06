package org.gocs.letmeknow.model.local;

import android.graphics.Bitmap;

import org.gocs.letmeknow.model.remote.User;

import java.io.Serializable;

/**
 * Created by dynamicheart on 7/6/2017.
 */

public class PersistableUser extends User implements Serializable{

    private static final long serialVersionUID = -1421969003346399347L;

    private boolean isLogin;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
