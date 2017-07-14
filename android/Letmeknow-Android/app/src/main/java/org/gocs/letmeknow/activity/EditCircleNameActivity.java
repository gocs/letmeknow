package org.gocs.letmeknow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.application.Constants;
import org.gocs.letmeknow.model.User;
import org.gocs.letmeknow.network.RetrofitClient;
import org.gocs.letmeknow.util.UserManager;
import org.gocs.letmeknow.util.handler.NetworkErrorHandler;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/7/14.
 */

public class EditCircleNameActivity extends BaseActivity {

    public static final String GROUP_ID = "GROUP_ID";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.input_circle_name)
    EditText editText;

    @BindView(R.id.button_edit_circle_name)
    Button button;

    String newGroupName;
    String groupId;

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initToolbar();

        button.setOnClickListener(view->{
            Intent intent = getIntent();
            groupId = (String)intent.getSerializableExtra(GROUP_ID);
            newGroupName = editText.getText().toString();
            RetrofitClient.getService().updateCircleName(groupId,newGroupName)
                    .flatMap(NetworkErrorHandler.ErrorFilter)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        finish();
                    }, NetworkErrorHandler.basicErrorHandler);
        });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_circle_edit_name;
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //set toolbar title
        getSupportActionBar().setTitle("circle_menu_edit_gname");
    }

}
