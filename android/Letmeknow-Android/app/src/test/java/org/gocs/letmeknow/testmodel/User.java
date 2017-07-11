package org.gocs.letmeknow.testmodel;

import java.util.List;
import java.util.Map;

/**
 * Created by dynamicheart on 7/10/2017.
 */

public class User {

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
