package org.gocs.letmeknow.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.application.App;
import org.gocs.letmeknow.network.RetrofitClient;
import org.gocs.letmeknow.util.NetworkErrorHandler;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dynamicheart on 6/30/2017.
 */

public class RegisterActivity extends BaseActivity{
    @BindView(R.id.text_register_username)
    EditText editTextUserName;
    @BindView(R.id.text_register_password)
    EditText editTextPassword;
    @BindView(R.id.button_register)
    Button buttonRegister;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buttonRegister.setOnClickListener(view->{
            String userName = editTextUserName.getText().toString();
            String password = editTextPassword.getText().toString();

            RetrofitClient.getService().register(userName, password)
                    .flatMap(NetworkErrorHandler.ErrorFilter)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response->{
                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFS_LOGIN_STATUS,MODE_PRIVATE).edit();
                        editor.putBoolean("login_status",true);
                        editor.apply();
                    },NetworkErrorHandler.basicErrorHandler);
        });

    }
}
