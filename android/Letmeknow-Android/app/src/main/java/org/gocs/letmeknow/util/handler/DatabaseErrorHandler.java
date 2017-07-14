package org.gocs.letmeknow.util.handler;

import android.widget.Toast;

import org.gocs.letmeknow.application.App;

import io.reactivex.functions.Consumer;

/**
 * Created by dynamicheart on 7/12/2017.
 */

public class DatabaseErrorHandler {

    static private void onError(Throwable e) {
        Toast.makeText(App.getInstance(),e.getMessage(),Toast.LENGTH_SHORT).show();
        e.printStackTrace();
    }

    static public final Consumer<Throwable> basicErrorHandler = throwable -> onError(throwable);
}
