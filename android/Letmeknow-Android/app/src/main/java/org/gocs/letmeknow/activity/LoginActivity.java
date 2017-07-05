package org.gocs.letmeknow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.service.ApiService;
import org.gocs.letmeknow.service.ServeiveGenerator;

import butterknife.BindView;
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
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                ApiService apiService = ServeiveGenerator.createService(ApiService.class);
                Call<Void> call = apiService.login(username,password);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
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
