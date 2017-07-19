package org.gocs.letmeknow.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.PushService;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.application.Constants;
import org.gocs.letmeknow.couchbase.DataBaseClient;
import org.gocs.letmeknow.fragment.CircleFragment;
import org.gocs.letmeknow.fragment.CircleIntroductionFragment;
import org.gocs.letmeknow.fragment.NotificationFragment;
import org.gocs.letmeknow.fragment.PrivateMessageFragment;
import org.gocs.letmeknow.fragment.ReadFragment;
import org.gocs.letmeknow.fragment.SendFragment;
import org.gocs.letmeknow.model.Circle;
import org.gocs.letmeknow.model.User;
import org.gocs.letmeknow.network.RetrofitClient;
import org.gocs.letmeknow.util.QRCodeUtils;
import org.gocs.letmeknow.util.ToastUtils;
import org.gocs.letmeknow.util.UserManager;
import org.gocs.letmeknow.util.handler.NetworkErrorHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.avos.avoscloud.Messages.OpType.result;
import static org.gocs.letmeknow.application.Constants.TAB_IMAGE_RES_ID;
import static org.gocs.letmeknow.application.Constants.TAB_NUM;

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
    @BindView(R.id.fam_item_create)
    FloatingActionButton floatingActionButtonCreate;
    @BindView(R.id.fam_item_drafts)
    FloatingActionButton floatingActionButtonDrafts;

    //can not bind view
    TextView textNavUserName;
    TextView textNavEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.tab_notification);

        PushService.setDefaultPushCallback(this, MainActivity.class);

        //can not inject
        View header = navigationView.getHeaderView(0);
        textNavUserName = (TextView) header.findViewById(R.id.text_nav_username);
        textNavEmail = (TextView) header.findViewById(R.id.text_nav_email);

        initDrawer();
        initTab();
        setUpFloatingActionMenu();
        detectInstallationIdChange();

        List<String> channels = new ArrayList<>();
        channels.add(UserManager.getCurrentUser().getUserId());
        DataBaseClient.startReplication(channels);
    }

    private void detectInstallationIdChange(){
        User user = UserManager.getCurrentUser();
        String userId = user.getUserId();
        String oldInstallationId = user.getInstallationId();
        String currentInstallationId = AVInstallation.getCurrentInstallation().getInstallationId();
        if(oldInstallationId == null || !oldInstallationId.equals(currentInstallationId)){
            RetrofitClient.getService().updateInstallationId(userId, currentInstallationId)
                    .flatMap(NetworkErrorHandler.ErrorFilter)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        user.setInstallationId(currentInstallationId);
                        UserManager.saveOrUpdateUser(user);
                    },NetworkErrorHandler.basicErrorHandler);
        }

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
        int id = item.getItemId();
        switch(id){
            case R.id.menu_Item_join:{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[] {Manifest.permission.CAMERA}, Constants.MY_PERMISSIONS_REQUEST_CAMERA);
                    }else {
                        startActivityForResult(new Intent(this, CaptureActivity.class),0);
                    }
                }else {
                    startActivityForResult(new Intent(this, CaptureActivity.class),0);
                }
                break;
            }
            default:;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Constants.MY_PERMISSIONS_REQUEST_CAMERA:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivityForResult(new Intent(this, CaptureActivity.class),0);
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
        }

        // other 'case' lines to check for other
        // permissions this app might request
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== this.RESULT_OK){
            Bundle bundle = data.getExtras();
            String res = bundle.getString("result");
            String groupId = QRCodeUtils.decodeQR(res);
            RetrofitClient.getService()
                    .joinGroup(groupId)
                    .flatMap(NetworkErrorHandler.ErrorFilter)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response->{
                        String result = (String)response.getMessage();
                        if(result.equals("success")){
                            ToastUtils.showShortToast("成功加入圈子");
                        }else{
                            ToastUtils.showShortToast("圈子不存在或您已在该圈子中");
                        }
                    }, NetworkErrorHandler.basicErrorHandler);
        }
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
        adapter.addFragment(new ReadFragment());
        adapter.addFragment(new SendFragment());
        adapter.addFragment(new CircleFragment());
        adapter.addFragment(new PrivateMessageFragment());
        viewPager.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
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

    private void initDrawer(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        User user = UserManager.getCurrentUser();
        Log.d("Navigation",user.toString());
        textNavUserName.setText(user.getUserName());
        textNavEmail.setText(user.getEmail());
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
                        getSupportActionBar().setTitle(R.string.tab_read);
                        break;
                    case 2:
                        getSupportActionBar().setTitle(R.string.tab_send);
                        break;
                    case 3:
                        getSupportActionBar().setTitle(R.string.tab_circle);
                        break;
                    case 4:
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
        floatingActionButtonCreate.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, NotificationEditActivity.class);
            startActivity(intent);
        });
    }


}
