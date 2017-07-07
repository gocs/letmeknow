package org.gocs.letmeknow.util;

import org.gocs.letmeknow.application.App;
import org.gocs.letmeknow.application.Constants;
import org.gocs.letmeknow.model.local.PersistableUser;
import org.gocs.letmeknow.model.remote.RemoteUser;

/**
 * Created by dynamicheart on 7/5/2017.
 */

public class UserManager {
    private static ACache aCache;

    public static PersistableUser getCurrentUser(){
        ACache aCache = getACacheInstance();
        return (PersistableUser)aCache.getAsObject(Constants.CURRENT_USER);
    }

    public static void changeLoginStatus(boolean loginStatus){
        ACache aCache = getACacheInstance();
        PersistableUser user = (PersistableUser)aCache.getAsObject(Constants.CURRENT_USER);
        user.setLogin(loginStatus);
        aCache.put(Constants.CURRENT_USER,user);
    }

    public static void saveOrUpdateUser(RemoteUser remoteUser, boolean loginStatus){
        ACache aCache = getACacheInstance();
        PersistableUser persistableUser = new PersistableUser();
        persistableUser.setUserId(remoteUser.getUserId());
        persistableUser.setUserName(remoteUser.getUserName());
        persistableUser.setPassword(remoteUser.getPassword());
        persistableUser.setPhoneNumber(remoteUser.getPhoneNumber());
        persistableUser.setEmail(remoteUser.getEmail());
        persistableUser.setAvatarUrl(remoteUser.getAvatarUrl());
        persistableUser.setLogin(loginStatus);
        aCache.put(Constants.CURRENT_USER, persistableUser);
    }

    private static ACache getACacheInstance(){
        if(aCache == null){
            aCache = ACache.get(App.getInstance());
        }
        return aCache;
    }


}
