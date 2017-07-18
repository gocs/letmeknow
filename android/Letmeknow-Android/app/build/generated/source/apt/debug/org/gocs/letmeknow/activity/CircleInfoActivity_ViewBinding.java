// Generated code from Butter Knife. Do not modify!
package org.gocs.letmeknow.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.gocs.letmeknow.R;

public class CircleInfoActivity_ViewBinding implements Unbinder {
  private CircleInfoActivity target;

  @UiThread
  public CircleInfoActivity_ViewBinding(CircleInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CircleInfoActivity_ViewBinding(CircleInfoActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.tablayout = Utils.findRequiredViewAsType(source, R.id.tablayout_circle_info, "field 'tablayout'", TabLayout.class);
    target.img_portrait = Utils.findRequiredViewAsType(source, R.id.img_portrait_circle_info, "field 'img_portrait'", ImageView.class);
    target.text_title = Utils.findRequiredViewAsType(source, R.id.text_title_circle_info, "field 'text_title'", TextView.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewpager_circle_info, "field 'viewPager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CircleInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.tablayout = null;
    target.img_portrait = null;
    target.text_title = null;
    target.viewPager = null;
  }
}
