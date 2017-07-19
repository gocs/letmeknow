package org.gocs.letmeknow.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by lenovo on 2017/7/19.
 */

public class PicassoImgUtil {

    public static void loadImgByInternetUrl(Context cxt, String url, ImageView imageView){
        Picasso.with(cxt).load(url).into(imageView);
    }

}
