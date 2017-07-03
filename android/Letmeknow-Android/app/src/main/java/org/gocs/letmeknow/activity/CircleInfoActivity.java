package org.gocs.letmeknow.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import org.gocs.letmeknow.R;
import org.gocs.letmeknow.fragment.CircleInformerFragment;
import org.gocs.letmeknow.fragment.CircleIntroductionFragment;
import org.gocs.letmeknow.fragment.CircleMembersFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static org.gocs.letmeknow.application.Constant.CIRCLE_TAB_IMAGE_ID;
import static org.gocs.letmeknow.application.Constant.TAB_NUM;

/**
 * Created by rebas on 2017/6/30.
 */

public class CircleInfoActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tablayout_circle_info)
    TabLayout tablayout_circle_info;

    @BindView(R.id.img_portrait_circle_info)
    ImageView img_portrait;

    @BindView(R.id.text_title_circle_info)
    TextView text_title;

    @BindView(R.id.viewpager_circle_info)
    ViewPager viewPager;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);

        initTab();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CircleIntroductionFragment(),getString(R.string.circle_info_intro));
        adapter.addFragment(new CircleInformerFragment(),getString(R.string.circle_info_informer));
        adapter.addFragment(new CircleMembersFragment(),getString(R.string.circle_info_members));
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

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

        private void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }

    private void initTab(){
        // setup TabLayout first
        setupViewPager(viewPager);
        tablayout_circle_info.setupWithViewPager(viewPager);

        // configure titles
        try {
            for (int i = 0; i < TAB_NUM; i++) {
                tablayout_circle_info.getTabAt(i).setText(CIRCLE_TAB_IMAGE_ID[i]);
            }
        } catch (NullPointerException e) {
            ;
        }
    }
}
