// Generated code from Butter Knife. Do not modify!
package org.gocs.letmeknow.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.gocs.letmeknow.R;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.drawer = Utils.findRequiredViewAsType(source, R.id.drawer_layout, "field 'drawer'", DrawerLayout.class);
    target.navigationView = Utils.findRequiredViewAsType(source, R.id.nav_view, "field 'navigationView'", NavigationView.class);
    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tablayout, "field 'tabLayout'", TabLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewpager, "field 'viewPager'", ViewPager.class);
    target.floatingActionMenu = Utils.findRequiredViewAsType(source, R.id.fam, "field 'floatingActionMenu'", FloatingActionMenu.class);
    target.floatingActionButtonCreate = Utils.findRequiredViewAsType(source, R.id.fam_item_create, "field 'floatingActionButtonCreate'", FloatingActionButton.class);
    target.floatingActionButtonDrafts = Utils.findRequiredViewAsType(source, R.id.fam_item_drafts, "field 'floatingActionButtonDrafts'", FloatingActionButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.drawer = null;
    target.navigationView = null;
    target.tabLayout = null;
    target.viewPager = null;
    target.floatingActionMenu = null;
    target.floatingActionButtonCreate = null;
    target.floatingActionButtonDrafts = null;
  }
}
