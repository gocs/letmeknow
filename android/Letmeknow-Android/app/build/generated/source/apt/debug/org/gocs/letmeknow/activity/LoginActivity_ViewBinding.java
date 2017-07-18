// Generated code from Butter Knife. Do not modify!
package org.gocs.letmeknow.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.gocs.letmeknow.R;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target, View source) {
    this.target = target;

    target.editTextUsername = Utils.findRequiredViewAsType(source, R.id.text_login_username, "field 'editTextUsername'", EditText.class);
    target.editTextPassword = Utils.findRequiredViewAsType(source, R.id.text_login_password, "field 'editTextPassword'", EditText.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progress_bar_login, "field 'progressBar'", ProgressBar.class);
    target.buttonLogin = Utils.findRequiredViewAsType(source, R.id.button_login, "field 'buttonLogin'", Button.class);
    target.textRegister = Utils.findRequiredViewAsType(source, R.id.text_register, "field 'textRegister'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editTextUsername = null;
    target.editTextPassword = null;
    target.progressBar = null;
    target.buttonLogin = null;
    target.textRegister = null;
  }
}
