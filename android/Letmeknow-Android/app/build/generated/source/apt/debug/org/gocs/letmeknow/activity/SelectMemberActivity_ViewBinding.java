// Generated code from Butter Knife. Do not modify!
package org.gocs.letmeknow.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.gocs.letmeknow.R;

public class SelectMemberActivity_ViewBinding implements Unbinder {
  private SelectMemberActivity target;

  @UiThread
  public SelectMemberActivity_ViewBinding(SelectMemberActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SelectMemberActivity_ViewBinding(SelectMemberActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.recyclerViewMemberList = Utils.findRequiredViewAsType(source, R.id.recyclerview_select_member_list, "field 'recyclerViewMemberList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectMemberActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.recyclerViewMemberList = null;
  }
}
