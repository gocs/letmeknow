package org.gocs.letmeknow.activity;


import android.os.Bundle;
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

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }


    @BindView(R.id.text_email)
    AutoCompleteTextView autoCompleteTextViewEmail;
    @BindView(R.id.text_password)
    EditText editTextPassword;
    @BindView(R.id.progress_bar_login)
    ProgressBar progressBar;
    @BindView(R.id.button_login)
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
