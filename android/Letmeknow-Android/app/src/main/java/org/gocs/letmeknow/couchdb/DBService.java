package org.gocs.letmeknow.couchdb;

import android.content.Context;

/**
 * Created by lenovo on 2017/7/10.
 */

public class DBService {

    private static DBWrapper Database;

    public static DBWrapper getService(String dbname, Context context){
        if(Database == null){
            Database = new DBWrapper(dbname,context);
        }
        return Database;
    }

}
