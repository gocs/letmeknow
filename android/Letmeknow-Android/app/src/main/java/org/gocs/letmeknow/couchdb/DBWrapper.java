package org.gocs.letmeknow.couchdb;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.couchbase.lite.*;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.replicator.Replication;

import android.content.Context;
import org.gocs.letmeknow.R;

/**
 * Created by lenovo on 2017/7/6.
 */

public class DBWrapper {

    private Database database;
    private android.content.Context ctx;
    private Manager manager;
    // keep a reference to a running replication to avoid GC
    private Replication replica;

    /**
     * Ctor
     * @param dbname
     * @param context
     */
    public DBWrapper(String dbname, Context context){

        this.ctx = context;
		/* Manages access to databases */
        try {
            manager = new Manager( new AndroidContext(ctx), Manager.DEFAULT_OPTIONS );
        }
        catch (IOException e) {
            ErrorChecker.ShowException(ctx, R.string.err_create_manager, e );
            return;
        }
        // create a name for the database and make sure the name is legal
        // Only the following characters are valid:
        // abcdefghijklmnopqrstuvwxyz0123456789_$()+-/
        if ( ! Manager.isValidDatabaseName(dbname)) {
            ErrorChecker.showError(ctx, R.string.err_db_name);
            return;
        }
        // get existing db with that name
        // or create a new one if it doesn't exist
        try {
            database = manager.getDatabase(dbname);
        }
        catch (CouchbaseLiteException e) {
            ErrorChecker.ShowException(ctx, R.string.err_no_db, e );
            return;
        }
    }

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
            Document doc = database.getDocument(docId);
            UnsavedRevision newRev = doc.getCurrentRevision().createRevision();
            newRev.setAttachment(attachName, mimeType, in);
            newRev.save();
        }
        catch (CouchbaseLiteException e) {
            ErrorChecker.ShowException(ctx, R.string.err_write_attach, e);
        }
    }


    /**
     * Get a given Document's attachment if any
     * @param docId
     * @param attachName
     * @return Attachment
     */
    public Attachment getAttachment(String docId, String attachName){

        Document doc = database.getDocument(docId);
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
            Document doc = database.getDocument(docId);
            UnsavedRevision newRev = doc.getCurrentRevision().createRevision();
            newRev.removeAttachment(attachName);
            // (You could also update newRev.properties while you're here)
            newRev.save();
        }
        catch (CouchbaseLiteException e) {
            ErrorChecker.ShowException(ctx, R.string.err_delete_attach, e );
        }
    }


	/* CRUD Operations *********************************/

    /**
     * C-rud
     * @param docContent
     * @return docId
     */
    public String create( Map<String, Object> docContent ){

        if( ! ErrorChecker.checkDb(ctx, database)){
            return "";
        }
        // create an empty document
        Document doc = database.createDocument();
        // add content to document and write the document to the database
        try {
            doc.putProperties(docContent);
        }
        catch (CouchbaseLiteException e) {
            ErrorChecker.ShowException(ctx, R.string.err_db_write, e ) ;
            return "";
        }
        return doc.getId();
    }

    /**
     * c-R-ud
     * @param docId
     * @return Doc content
     */
    public Map<String, Object> read(String docId){

        if( ! ErrorChecker.checkDb(ctx, database)){
            return new HashMap<String, Object>();//empty
        }
        // retrieve the document from the database
        Document doc = database.getDocument(docId);
        // display the retrieved document
        return doc.getProperties();
    }


    /**
     * cr-U-d
     * @param key
     * @param value
     * @param docId
     * @return success or failure
     */
    public boolean update( final String key, final Object value, String docId ){

        if( ! ErrorChecker.checkDb(ctx, database)){
            return false;
        }
        // update the document
        try {
            Document doc = database.getDocument(docId);

            // this alternative way is better for handling write conflicts
            doc.update(new Document.DocumentUpdater() {
                @Override
                public boolean update(UnsavedRevision newRevision) {
                    Map<String, Object> properties = newRevision.getUserProperties();
                    properties.put(key, value);
                    newRevision.setUserProperties(properties);
                    return true;
                }
            });

		/*	Map<String, Object> docContent = doc.getProperties();
			//Working on a copy
			Map<String, Object> updatedContent = new HashMap<String, Object>();
			updatedContent.putAll(docContent);
			updatedContent.put(key, value);
			doc.putProperties(updatedContent);*/
        }
        catch (CouchbaseLiteException e) {
            ErrorChecker.ShowException(ctx, R.string.err_db_update, e ) ;
            return false;
        }
        return true;
    }


    /**
     * cru-D
     * @param docId
     * @return
     */
    public boolean delete(String docId){

        if( ! ErrorChecker.checkDb(ctx, database)){
            return false;
        }
        Document doc = null;
        // delete the document
        try {
            doc = database.getDocument(docId);
            doc.delete();
        }
        catch (CouchbaseLiteException e) {
            ErrorChecker.ShowException(ctx, R.string.err_db_delete, e ) ;
        }
        return  doc.isDeleted();
    }

}
