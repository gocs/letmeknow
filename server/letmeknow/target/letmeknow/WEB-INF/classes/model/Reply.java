package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/7/4.
 */
public class Reply {
    private Integer code;
    private String message;
    private Map<String,Object> data=new HashMap<String,Object>();

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Reply(Integer code, String message, Map<String,Object> data){
        this.code=code;
        this.message=message;
        this.data=data;
    }

    public Reply(){}
}
