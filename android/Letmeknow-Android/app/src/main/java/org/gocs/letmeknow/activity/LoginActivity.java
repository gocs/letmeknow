package org.gocs.letmeknow.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.application.App;
import org.gocs.letmeknow.network.RetrofitClient;
import org.gocs.letmeknow.service.LMKService;
import org.gocs.letmeknow.util.NetworkErrorHandler;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFS_LOGIN_STATUS,MODE_PRIVATE).edit();
                            editor.putBoolean("login_status",true);
                            editor.apply();
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
