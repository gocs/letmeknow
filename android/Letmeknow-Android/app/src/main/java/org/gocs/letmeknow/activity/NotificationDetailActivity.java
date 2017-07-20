package org.gocs.letmeknow.activity;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.model.Notification;
import org.gocs.letmeknow.service.NotificationPersistService;
import org.gocs.letmeknow.util.handler.DatabaseErrorHandler;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by dynamicheart on 6/30/2017.
 */

public class NotificationDetailActivity extends BaseActivity{

    public static final String NOTIFICATION_SERIALIZABLE = "NOTIFICATION_SERIALIZABLE";

    public static final String NOTIFICATION_ID = "notificationId";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.text_notification_content)
    TextView textViewNotificationContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();

        Intent intent = getIntent();
        Notification notification = (Notification) intent.getSerializableExtra(NOTIFICATION_SERIALIZABLE);
        if(notification != null){
            textViewNotificationContent.setText(notification.getContent());
        }else {
            String notificationId = intent.getStringExtra(NOTIFICATION_ID);
            NotificationPersistService.findOne(notificationId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(dbNotification ->{
                        textViewNotificationContent.setText(dbNotification.getContent());
                    }, DatabaseErrorHandler.basicErrorHandler);
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_notification_detail;
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //set toolbar title
        getSupportActionBar().setTitle(R.string.notification_title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.notification_detail_menu, menu);
        return true;
    }
}
