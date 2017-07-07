package org.gocs.letmeknow.model.common.Component;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dynamicheart on 7/7/2017.
 */

public class Choice implements Serializable{

    private static final long serialVersionUID = -1213086697336399050L;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
