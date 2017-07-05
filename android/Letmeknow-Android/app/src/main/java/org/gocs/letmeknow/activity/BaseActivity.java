package org.gocs.letmeknow.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
/**
 * Created by dynamicheart on 6/26/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    final protected static String SHARED_PREFS_LOGIN_STATUS = "LOGIN_STATUS";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
    }

    abstract protected int getContentViewId();
}
