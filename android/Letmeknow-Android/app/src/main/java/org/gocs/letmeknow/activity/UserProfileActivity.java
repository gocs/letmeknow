package org.gocs.letmeknow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Toast;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.application.App;
import org.gocs.letmeknow.network.RetrofitClient;
import org.gocs.letmeknow.util.NetworkErrorHandler;
import org.gocs.letmeknow.util.UserManager;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
        button.setOnClickListener(view -> {
            RetrofitClient.getService().logout()
                    .flatMap(NetworkErrorHandler.ErrorFilter)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        UserManager.changeLoginStatus(false);
                        Toast.makeText(App.getInstance(),"注销成功",Toast.LENGTH_SHORT).show();
                    });
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
