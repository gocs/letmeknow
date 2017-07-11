package org.gocs.letmeknow.util;


import android.widget.Toast;

import org.gocs.letmeknow.application.App;
import org.gocs.letmeknow.model.HttpResponse;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by dynamicheart on 7/5/2017.
 */

public class NetworkErrorHandler {

    static private void onError(Throwable e) {
        Toast.makeText(App.getInstance(),e.getMessage(),Toast.LENGTH_SHORT).show();
        e.printStackTrace();
    }

    static public final Consumer<Throwable> basicErrorHandler = throwable -> onError(throwable);

    static public final Function<HttpResponse, Observable<HttpResponse>> ErrorFilter =
            response->{
                if(response.getCode() == 1){
                    return Observable.just(response);
                }else {
                    return Observable.error(new Exception(response.getMessage()));
                }

            };
}
