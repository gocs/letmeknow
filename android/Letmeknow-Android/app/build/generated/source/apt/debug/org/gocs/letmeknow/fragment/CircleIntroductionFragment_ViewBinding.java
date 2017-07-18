// Generated code from Butter Knife. Do not modify!
package org.gocs.letmeknow.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.gocs.letmeknow.R;

public class CircleIntroductionFragment_ViewBinding implements Unbinder {
  private CircleIntroductionFragment target;

  @UiThread
  public CircleIntroductionFragment_ViewBinding(CircleIntroductionFragment target, View source) {
    this.target = target;

    target.introTV = Utils.findRequiredViewAsType(source, R.id.text_circle_introduction, "field 'introTV'", TextView.class);
    target.masterTV = Utils.findRequiredViewAsType(source, R.id.text_circle_owner, "field 'masterTV'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CircleIntroductionFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.introTV = null;
    target.masterTV = null;
  }
}
