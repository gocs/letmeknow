package org.gocs.letmeknow.util;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.application.App;

import java.io.File;

import static org.gocs.letmeknow.application.Constants.BASE_IMG_URL;

/**
 * Created by lenovo on 2017/7/19.
 */

public class PicassoImgUtils {

    public static void loadImgByInternetUrl(Context cxt, String url, ImageView imageView){
        Picasso.with(cxt).load(url).into(imageView);
    }

    public static void loadImgByRawUrl(Context cxt, String rawUrl, ImageView imageView){
        if(rawUrl != null){
            Picasso.with(cxt).load(BASE_IMG_URL + rawUrl).into(imageView);
        }
    }

    public static void loadImgByFile(Context cxt, File file, ImageView imageView){
        Picasso.with(cxt).load(file).into(imageView);
    }

    public static void loadImgByUri(Context cxt, Uri uri, ImageView imageView){
        Picasso.with(cxt).load(uri).into(imageView);
    }
}
