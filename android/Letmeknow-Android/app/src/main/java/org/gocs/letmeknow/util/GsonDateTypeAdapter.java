package org.gocs.letmeknow.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo on 2017/7/4.
 */

public class GsonDateTypeAdapter extends TypeAdapter<Date> {

    @Override
    public void write(JsonWriter out, Date value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(value.getTime());
        }
    }

    @Override
    public Date read(JsonReader in) throws IOException {
        if (in.peek() == null) {
            return null;
        }
        String str = in. nextString();
        Date d = null;
        try {
            d = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(str);
        } catch (Exception e) {
        }
        return d;
    }

}
