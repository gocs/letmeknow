package org.gocs.letmeknow.model.common;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by dynamicheart on 7/5/2017.
 */

public class LMKHttpResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
