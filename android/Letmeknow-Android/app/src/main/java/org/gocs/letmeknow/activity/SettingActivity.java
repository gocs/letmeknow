package org.gocs.letmeknow.activity;

import android.support.v7.widget.Toolbar;

import org.gocs.letmeknow.R;

import butterknife.BindView;

/**
 * Created by dynamicheart on 6/30/2017.
 */

public class SettingActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_setting;
    }

}
