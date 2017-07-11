package org.gocs.letmeknow.util;

import android.widget.Toast;

import org.gocs.letmeknow.application.App;

/**
 * Created by dynamicheart on 7/11/2017.
 */

public class ToastUtils {

    public static void showShortToast(String message){
        Toast.makeText(App.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(String message){
        Toast.makeText(App.getInstance(), message, Toast.LENGTH_LONG).show();
    }
}
