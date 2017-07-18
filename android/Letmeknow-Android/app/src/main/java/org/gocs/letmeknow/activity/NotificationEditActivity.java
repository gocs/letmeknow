package org.gocs.letmeknow.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.util.ToastUtils;

import butterknife.BindView;

/**
 * Created by dynamicheart on 7/7/2017.
 */

public class NotificationEditActivity extends BaseActivity{
    @BindView(R.id.toolbar)
    Toolbar toolbar;

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
        toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.ic_menu_send));

        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //set toolbar title
        getSupportActionBar().setTitle(R.string.edit_titile);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        ToastUtils.showShortToast("click");
        return true;
    }
}
