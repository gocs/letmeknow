// Generated code from Butter Knife. Do not modify!
package org.gocs.letmeknow.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.gocs.letmeknow.R;

public class NotificationFragment_ViewBinding implements Unbinder {
  private NotificationFragment target;

  @UiThread
  public NotificationFragment_ViewBinding(NotificationFragment target, View source) {
    this.target = target;

    target.recyclerViewNoticationList = Utils.findRequiredViewAsType(source, R.id.recyclerView_notification, "field 'recyclerViewNoticationList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NotificationFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerViewNoticationList = null;
  }
}
