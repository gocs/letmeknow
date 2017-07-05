package org.gocs.letmeknow.activity;

import android.widget.EditText;

import org.gocs.letmeknow.R;

import butterknife.BindView;

/**
 * Created by dynamicheart on 6/30/2017.
 */

public class RegisterActivity extends BaseActivity{
    @BindView(R.id.text_register_username)
    EditText editTextUserName;
    @BindView(R.id.text_register_password)
    EditText editTextPassword;
    @BindView(R.id.text_confirm_password)
    EditText editTextConfirmPassword;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
    }


}
