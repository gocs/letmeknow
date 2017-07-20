package org.gocs.letmeknow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.application.Constants;
import org.gocs.letmeknow.model.User;
import org.gocs.letmeknow.util.manager.network.RetrofitClient;
import org.gocs.letmeknow.util.event.UserLoginEvent;
import org.gocs.letmeknow.util.handler.NetworkErrorHandler;
import org.gocs.letmeknow.util.manager.cache.UserManager;
import org.greenrobot.eventbus.EventBus;

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
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        User user = (User)response.getData().get(Constants.JSON_KEY_USER);
                        UserManager.saveOrUpdateUser(user);
                        UserManager.changeLoginStatus(true);
                        EventBus.getDefault().post(new UserLoginEvent(UserLoginEvent.LoginType.REGISTER));
                    },NetworkErrorHandler.basicErrorHandler);
        });

    }
}
