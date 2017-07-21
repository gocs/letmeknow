package org.gocs.letmeknow.util.manager.couchbase;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.couchbase.lite.*;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.replicator.Replication;
import com.couchbase.lite.util.Log;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.application.App;
import org.gocs.letmeknow.application.Constants;
import org.gocs.letmeknow.util.ToastUtils;

/**
 * Created by lenovo on 2017/7/6.
 */

public class DataBaseClient{
    private static final String TAG = "Couchbase";

    private static final String SYNC_URL_HTTP = "http://106.14.1.142:4984/notification";

    private static Database database;
    private static Manager manager;

    private static Replication pull;
    private static Replication push;

    public static Database getCouchDBInstance(){
        if(database == null){
            try {
                Manager manager = getManagerInstance();
                if ( manager != null) {
                    database = manager.getDatabase(Constants.COUCHDB_NAME);
                }
            }catch (Exception ignore){

            }
        }
        return database;
    }

    private static Manager getManagerInstance(){
        if(manager == null){
            try{
                enableLogging();
                manager = new Manager(new AndroidContext(App.getInstance()), Manager.DEFAULT_OPTIONS);
            }catch (Exception e){
                return null;
            }
        }
        return manager;
    };


    /** Release all resources and close all Databases. */
    public void close(){
        if(manager != null){
            manager.close();
        }
    }

    /**
     * A replica can be active/stopped/off-line/idle
     * @param rep
     * @return
     */
    public boolean isReplicaActive( Replication rep){
        return rep != null && (rep.getStatus() ==
                Replication.ReplicationStatus.REPLICATION_ACTIVE);
    }

	/* Attachments *********************************/

    /**
     * Write an Attachment for a given Document
     * @param docId
     * @param attachName
     * @param mimeType  e.g. "image/jpeg"
     * @param in
     */
    public void writeAttachment(String docId, String attachName,
                                String mimeType, InputStream in){

        try {
            Document doc = getCouchDBInstance().getDocument(docId);
            UnsavedRevision newRev = doc.getCurrentRevision().createRevision();
            newRev.setAttachment(attachName, mimeType, in);
            newRev.save();
        }
        catch (CouchbaseLiteException e) {
            ToastUtils.showShortToast(App.getInstance().getString(R.string.err_write_attach));
        }
    }


    /**
     * Get a given Document's attachment if any
     * @param docId
     * @param attachName
     * @return Attachment
     */
    public Attachment getAttachment(String docId, String attachName){

        Document doc = getCouchDBInstance().getDocument(docId);
        Revision rev = doc.getCurrentRevision();
        return rev.getAttachment(attachName);
    }

    /**
     * Remove an Attachment from a Document
     * @param docId
     * @param attachName
     */
    public void deleteAttachment(String docId, String attachName){

        try {
            Document doc = getCouchDBInstance().getDocument(docId);
            UnsavedRevision newRev = doc.getCurrentRevision().createRevision();
            newRev.removeAttachment(attachName);
            // (You could also update newRev.properties while you're here)
            newRev.save();
        }
        catch (CouchbaseLiteException e) {
            ToastUtils.showShortToast(App.getInstance().getString(R.string.err_delete_attach));
        }
    }

    private static void enableLogging() {
        Manager.enableLogging(TAG, Log.VERBOSE);
        Manager.enableLogging(Log.TAG, Log.VERBOSE);
        Manager.enableLogging(Log.TAG_SYNC_ASYNC_TASK, Log.VERBOSE);
        Manager.enableLogging(Log.TAG_SYNC, Log.VERBOSE);
        Manager.enableLogging(Log.TAG_QUERY, Log.VERBOSE);
        Manager.enableLogging(Log.TAG_VIEW, Log.VERBOSE);
        Manager.enableLogging(Log.TAG_DATABASE, Log.VERBOSE);
    }

    public static URL getSyncUrl() {
        URL url = null;
        try {
            url = new URL(SYNC_URL_HTTP);
        } catch (MalformedURLException e) {
            Log.e(TAG, "Invalid sync url", e);
        }
        return url;
    }

    public static void startReplication(List<String> channels) {
        if (pull == null) {
            pull = getCouchDBInstance().createPullReplication(getSyncUrl());
            pull.setChannels(channels);
            pull.setContinuous(true);
        }

        pull.stop();
        pull.start();


        if (push == null) {
            push = getCouchDBInstance().createPushReplication(getSyncUrl());
            push.setContinuous(true);
        }

        push.stop();
        push.start();
    }

    public static void stopReplication(){
        if(pull != null){
            pull.stop();
        }
        if(push != null){
            push.stop();
        }
    }

    public static Replication getPullRelication(){
        return pull;
    }

    public static Replication getPushReplication(){
        return push;
    }
}
