package org.gocs.letmeknow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import org.gocs.letmeknow.R;

import butterknife.BindView;

/**
 * Created by dynamicheart on 7/7/2017.
 */

public class NotificationEditActivity extends BaseActivity{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imagebutton_select_circle)
    ImageButton buttonAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_notification_edit;
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //set toolbar title
        getSupportActionBar().setTitle(R.string.edit_titile);

        buttonAdd.setOnClickListener(v -> {
            Intent intent = new Intent(NotificationEditActivity.this, SelectCircleActivity.class);
            startActivity(intent);
        });
    }
}
