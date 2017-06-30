package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/6/29.
 */
public class TestUtil {
    public static List<String> read (String filename) throws IOException {
        List<String> res= new ArrayList<String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        String data = null;
        while((data = br.readLine())!=null)
            res.add(data);
        return res;
    }
    public static String[] parse(String data){
        return data.split(" ");
    }
}
