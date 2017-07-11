package org.gocs.letmeknow.couchdb;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.couchbase.lite.*;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.replicator.Replication;
import com.couchbase.lite.util.Log;

import android.app.Application;
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

    public String test_create(){
        Map<String, Object> resultformat = new HashMap<String, Object>();
        resultformat.put("option1","hold the meeting today");
        resultformat.put("option2","hold the meeting tomorrow");
        Map<String, Object> currentresult = new HashMap<String, Object>();
        currentresult.put("option1","yes");
        currentresult.put("text1","hahaha");
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("receiver","2333");
        result.put("status",1);
        result.put("current result",currentresult);
        Map<String, Object> doc = new HashMap<String, Object>();
        doc.put("object_id","j124-jak4-432j-8tj4");
        doc.put("group_id","6666");
        doc.put("sender_id","5555");
        doc.put("content","we'll have a meeting");
        doc.put("attachment",null);
        doc.put("resultformat",resultformat);
        doc.put("result",result);
        doc.put("type","notification");
        Document document = database.createDocument();
        try{
            document.putProperties(doc);
            return document.getId();
        }catch(CouchbaseLiteException e){
            System.out.println("create_test failed");
            return null;
        }
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

    public List<Object> getDocByGroupId(String gid){
        final Query query = this.getGroupIdView().createQuery();

        //List<Object> startKeys = new ArrayList<Object>();
        //startKeys.add(gid);
        //List<Object> endKeys = new ArrayList<Object>();
        //endKeys.add(gid);
        query.setStartKey(gid);
        query.setEndKey(gid);

        List<Object> resultList = new ArrayList<Object>();
        try{
            QueryEnumerator result = query.run();
            while(result.hasNext()){
                QueryRow notification = result.next();
                resultList.add(notification.getValue());
            }
        }catch (CouchbaseLiteException e) {
            System.out.println("query run error.");
        }
        return resultList;
    }

    public List<Object> getDocBySenderId(String sid){
        final Query query = this.getSenderIdView().createQuery();
        query.setStartKey(sid);
        query.setEndKey(sid);

        List<Object> resultList = new ArrayList<Object>();
        try{
            QueryEnumerator result = query.run();
            while(result.hasNext()){
                QueryRow notification = result.next();
                resultList.add(notification.getValue());
            }
        }catch (CouchbaseLiteException e) {
            System.out.println("query run error.");
        }
        return resultList;
    }

    public List<Object> getDocByReceiverId(String rid){
        final Query query = this.getReceiverIdView().createQuery();
        query.setStartKey(rid);
        query.setEndKey(rid);

        List<Object> resultList = new ArrayList<Object>();
        try{
            QueryEnumerator result = query.run();
            while(result.hasNext()){
                QueryRow notification = result.next();
                resultList.add(notification.getValue());
            }
        }catch (CouchbaseLiteException e) {
            System.out.println("query run error.");
        }
        return resultList;
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

    /**
     * Database View
     */

    public View getGroupIdView() {
        View view = database.getView("group_id_view");
        if(view.getMap() == null){
            Mapper mapper = new Mapper(){
                public void map(Map<String, Object> document, Emitter emitter) {
                    String type = (String)document.get("type");
                    if ("notification".equals(type))
                        emitter.emit(document.get("group_id"), document);
                }
            };
            view.setMap(mapper, "3.0");
        }
        return view;
    }

    public View getSenderIdView() {
        View view = database.getView("sender_id_view");
        if(view.getMap() == null){
            Mapper mapper = new Mapper(){
                public void map(Map<String, Object> document, Emitter emitter) {
                    String type = (String)document.get("type");
                    if ("notification".equals(type))
                        emitter.emit(document.get("sender_id"), document);
                }
            };
            view.setMap(mapper, "3.0");
        }
        return view;
    }

    public View getReceiverIdView() {
        View view = database.getView("receiver_id_view");
        if(view.getMap() == null){
            Mapper mapper = new Mapper(){
                public void map(Map<String, Object> document, Emitter emitter) {
                    String type = (String)document.get("type");
                    if ("notification".equals(type)){
                        Map<String,Object> result = (Map<String,Object>)document.get("result");
                        emitter.emit(result.get("receiver"),document);
                    }
                }
            };
            view.setMap(mapper, "3.0");
        }
        return view;
    }

}
