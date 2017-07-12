package org.gocs.letmeknow.model.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

/**
 * Created by dynamicheart on 7/12/2017.
 */

public class DocumentEntity {
    @JsonIgnore
    private Map<String, Object> document;

    public Map<String, Object> getDocument() {

        return document;
    }

    public void setDocument(Map<String, Object> document) {
        this.document = document;
    }
}
