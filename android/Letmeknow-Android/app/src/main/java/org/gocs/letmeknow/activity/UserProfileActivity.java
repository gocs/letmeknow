package org.gocs.letmeknow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.application.App;
import org.gocs.letmeknow.model.User;
import org.gocs.letmeknow.network.OkHttpProvider;
import org.gocs.letmeknow.network.RetrofitClient;
import org.gocs.letmeknow.util.handler.NetworkErrorHandler;
import org.gocs.letmeknow.util.UserManager;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static org.gocs.letmeknow.util.PicassoImgUtil.loadImgByInternetUrl;

/**
 * Created by dynamicheart on 6/30/2017.
 */

public class UserProfileActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.button_logoff)
    Button button;

    @BindView(R.id.layout_user_username)
    RelativeLayout layout_username;

    @BindView(R.id.layout_user_name)
    RelativeLayout layout_name;

    @BindView(R.id.layout_user_phone)
    RelativeLayout layout_phone;

    @BindView(R.id.layout_user_email)
    RelativeLayout layout_email;

    @BindView(R.id.text_user_username)
    TextView text_username;

    @BindView(R.id.text_user_name)
    TextView text_name;

    @BindView(R.id.text_user_phone)
    TextView text_phone;

    @BindView(R.id.text_user_email)
    TextView text_email;

    @BindView(R.id.image_user_portrait)
    ImageView image_avatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        initData();
        button.setOnClickListener(view -> {
            Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            UserManager.changeLoginStatus(false);
            OkHttpProvider.clearCookie();

            RetrofitClient.getService().logout()
                    .flatMap(NetworkErrorHandler.ErrorFilter)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        Toast.makeText(App.getInstance(),"注销成功",Toast.LENGTH_SHORT).show();
                    }, NetworkErrorHandler.basicErrorHandler);
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

    private void initData(){
        User user = UserManager.getCurrentUser();
        text_username.setText(user.getUserName());
        text_phone.setText(user.getPhoneNumber());
        text_email.setText(user.getEmail());
        loadImgByInternetUrl(this,"http://106.15.179.41:8080/letmeknow/img/a.png",image_avatar);
    }
}
