package org.gocs.letmeknow.testmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by dynamicheart on 7/10/2017.
 */

public class User {

    @JsonProperty("fuck")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    private Map<String,Object> phones;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getPhones() {
        return phones;
    }

    public void setPhones(Map<String, Object> phones) {
        this.phones = phones;
    }
}
