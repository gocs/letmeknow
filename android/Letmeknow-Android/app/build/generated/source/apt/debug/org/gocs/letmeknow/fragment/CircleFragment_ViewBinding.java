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

public class CircleFragment_ViewBinding implements Unbinder {
  private CircleFragment target;

  @UiThread
  public CircleFragment_ViewBinding(CircleFragment target, View source) {
    this.target = target;

    target.recyclerViewCircleList = Utils.findRequiredViewAsType(source, R.id.recyclerview_circle_list, "field 'recyclerViewCircleList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CircleFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerViewCircleList = null;
  }
}
