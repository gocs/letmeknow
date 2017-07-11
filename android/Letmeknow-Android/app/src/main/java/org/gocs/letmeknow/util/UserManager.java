package org.gocs.letmeknow.util;

import org.gocs.letmeknow.application.App;
import org.gocs.letmeknow.application.Constants;
import org.gocs.letmeknow.model.User;

/**
 * Created by dynamicheart on 7/5/2017.
 */

public class UserManager {
    private static ACache aCache;

    public static User getCurrentUser(){
        ACache aCache = getACacheInstance();
        return (User)aCache.getAsObject(Constants.CURRENT_USER);
    }

    public static void changeLoginStatus(boolean loginStatus){
        ACache aCache = getACacheInstance();
        User user = (User)aCache.getAsObject(Constants.CURRENT_USER);
        user.setLogin(loginStatus);
        aCache.put(Constants.CURRENT_USER,user);
    }

    public static void saveOrUpdateUser(User user){
        ACache aCache = getACacheInstance();
        aCache.put(Constants.CURRENT_USER, user);
    }

    private static ACache getACacheInstance(){
        if(aCache == null){
            aCache = ACache.get(App.getInstance());
        }
        return aCache;
    }


}
