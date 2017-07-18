// Generated code from Butter Knife. Do not modify!
package org.gocs.letmeknow.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.gocs.letmeknow.R;

public class UserProfileActivity_ViewBinding implements Unbinder {
  private UserProfileActivity target;

  @UiThread
  public UserProfileActivity_ViewBinding(UserProfileActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UserProfileActivity_ViewBinding(UserProfileActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.button = Utils.findRequiredViewAsType(source, R.id.button_logoff, "field 'button'", Button.class);
    target.layout_username = Utils.findRequiredViewAsType(source, R.id.layout_user_username, "field 'layout_username'", RelativeLayout.class);
    target.layout_name = Utils.findRequiredViewAsType(source, R.id.layout_user_name, "field 'layout_name'", RelativeLayout.class);
    target.layout_phone = Utils.findRequiredViewAsType(source, R.id.layout_user_phone, "field 'layout_phone'", RelativeLayout.class);
    target.layout_email = Utils.findRequiredViewAsType(source, R.id.layout_user_email, "field 'layout_email'", RelativeLayout.class);
    target.text_username = Utils.findRequiredViewAsType(source, R.id.text_user_username, "field 'text_username'", TextView.class);
    target.text_name = Utils.findRequiredViewAsType(source, R.id.text_user_name, "field 'text_name'", TextView.class);
    target.text_phone = Utils.findRequiredViewAsType(source, R.id.text_user_phone, "field 'text_phone'", TextView.class);
    target.text_email = Utils.findRequiredViewAsType(source, R.id.text_user_email, "field 'text_email'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UserProfileActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.button = null;
    target.layout_username = null;
    target.layout_name = null;
    target.layout_phone = null;
    target.layout_email = null;
    target.text_username = null;
    target.text_name = null;
    target.text_phone = null;
    target.text_email = null;
  }
}
