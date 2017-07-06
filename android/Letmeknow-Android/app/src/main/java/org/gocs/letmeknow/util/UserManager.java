package org.gocs.letmeknow.util;

import org.gocs.letmeknow.application.App;
import org.gocs.letmeknow.application.Constants;
import org.gocs.letmeknow.model.local.PersistableUser;
import org.gocs.letmeknow.model.remote.User;

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
    }

    public static void saveOrUpdateUser(User user, boolean loginStatus){
        ACache aCache = getACacheInstance();
        PersistableUser persistableUser = new PersistableUser();
        persistableUser.setUserName(user.getUserName());
        persistableUser.setPassword(user.getPassword());
        persistableUser.setPhoneNumber(user.getPhoneNumber());
        persistableUser.setEmail(user.getEmail());
        persistableUser.setAvatarUrl(user.getAvatarUrl());
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
