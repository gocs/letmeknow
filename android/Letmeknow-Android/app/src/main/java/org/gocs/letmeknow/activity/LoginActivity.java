package org.gocs.letmeknow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVInstallation;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.application.Constants;
import org.gocs.letmeknow.model.User;
import org.gocs.letmeknow.network.RetrofitClient;
import org.gocs.letmeknow.util.event.UserLoginEvent;
import org.gocs.letmeknow.util.handler.NetworkErrorHandler;
import org.gocs.letmeknow.util.UserManager;
import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by dynamicheart on 6/30/2017.
 */

public class LoginActivity extends BaseActivity{
    @BindView(R.id.text_login_username)
    EditText editTextUsername;
    @BindView(R.id.text_login_password)
    EditText editTextPassword;
    @BindView(R.id.progress_bar_login)
    ProgressBar progressBar;
    @BindView(R.id.button_login)
    Button buttonLogin;
    @BindView(R.id.text_register)
    TextView textRegister;
    @BindView(R.id.login_background)
    LinearLayout background;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        background.setBackground(getResources().getDrawable(R.drawable.login_background));
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                RetrofitClient.getService().login(userName, password)
                        .flatMap(NetworkErrorHandler.ErrorFilter)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            User user = (User)response.getData().get(Constants.JSON_KEY_USER);
                            UserManager.saveOrUpdateUser(user);
                            UserManager.changeLoginStatus(true);
                            EventBus.getDefault().post(new UserLoginEvent(UserLoginEvent.LoginType.LOGIN));
                        }, NetworkErrorHandler.basicErrorHandler);

            }
        });
        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }


}
