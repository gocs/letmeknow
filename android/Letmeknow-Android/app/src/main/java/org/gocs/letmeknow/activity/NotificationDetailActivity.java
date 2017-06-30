package org.gocs.letmeknow.activity;

import org.gocs.letmeknow.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import butterknife.BindView;

/**
 * Created by dynamicheart on 6/30/2017.
 */

public class NotificationDetailActivity extends BaseActivity{

    @BindView(R.id.toolbar_notification_detail)
    Toolbar toolbar;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_notification_detail;
    }
}
