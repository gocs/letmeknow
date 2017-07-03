package org.gocs.letmeknow.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dynamicheart on 7/3/2017.
 */


public class User {
    @SerializedName("username")
    private String userName;
    @SerializedName("paswword")
    private String password;
}
