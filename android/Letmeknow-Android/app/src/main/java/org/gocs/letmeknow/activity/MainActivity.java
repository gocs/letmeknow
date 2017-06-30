package org.gocs.letmeknow.activity;

import android.content.Intent;
import android.os.Bundle;
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
import android.view.Menu;
import android.view.MenuItem;

import com.github.clans.fab.FloatingActionMenu;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.fragment.CircleFragment;
import org.gocs.letmeknow.fragment.NotificationFragment;
import org.gocs.letmeknow.fragment.PrivateMessageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static org.gocs.letmeknow.constant.Constant.TAB_IMAGE_RES_ID;
import static org.gocs.letmeknow.constant.Constant.TAB_NUM;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.fam)
    FloatingActionMenu floatingActionMenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        //set default title
        getSupportActionBar().setTitle(R.string.tab_notification);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        initTab();
        setUpFloatingActionMenu();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_profile:
                Intent profileIntent = new Intent(this, UserProfileActivity.class);
                this.startActivity(profileIntent);
                break;
            case R.id.nav_setting:
                Intent settingIntent = new Intent(this, SettingActivity.class);
                this.startActivity(settingIntent);
                break;
            case R.id.nav_about:
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                this.startActivity(aboutIntent);
                break;
            default:
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new NotificationFragment());
        adapter.addFragment(new CircleFragment());
        adapter.addFragment(new PrivateMessageFragment());
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        private ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        private void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }

    private void initTab() {
        // setup TabLayout first
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        getSupportActionBar().setTitle(R.string.tab_notification);
                        break;
                    case 1:
                        getSupportActionBar().setTitle(R.string.tab_circle);
                        break;
                    case 2:
                        getSupportActionBar().setTitle(R.string.tab_pm);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        // configure icons
        try {
            for (int i = 0; i < TAB_NUM; i++) {
                tabLayout.getTabAt(i).setIcon(TAB_IMAGE_RES_ID[i]);
            }
        } catch (NullPointerException e) {
            ;
        }
    }

    private void setUpFloatingActionMenu(){
        floatingActionMenu.setClosedOnTouchOutside(true);
    }
}
