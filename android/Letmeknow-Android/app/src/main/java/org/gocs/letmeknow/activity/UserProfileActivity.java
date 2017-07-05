package org.gocs.letmeknow.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.application.App;

import butterknife.BindView;

/**
 * Created by dynamicheart on 6/30/2017.
 */

public class UserProfileActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.button_logoff)
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        button.setOnClickListener(view ->{
            Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
            startActivity(intent);
            SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFS_LOGIN_STATUS,MODE_PRIVATE).edit();
            editor.remove("login_status");
            editor.apply();
        });

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_profile;
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //set toolbar title
        getSupportActionBar().setTitle(R.string.user_info_title);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
