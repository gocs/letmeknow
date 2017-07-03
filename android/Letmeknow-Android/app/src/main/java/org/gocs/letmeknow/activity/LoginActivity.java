package org.gocs.letmeknow.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.gocs.letmeknow.R;

import butterknife.BindView;


/**
 * Created by dynamicheart on 6/30/2017.
 */

public class LoginActivity extends BaseActivity{
    @BindView(R.id.text_username)
    EditText editTextUsername;
    @BindView(R.id.text_password)
    EditText editTextPassword;
    @BindView(R.id.progress_bar_login)
    ProgressBar progressBar;
    @BindView(R.id.button_login)
    Button buttonLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }



}
