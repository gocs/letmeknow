// Generated code from Butter Knife. Do not modify!
package org.gocs.letmeknow.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.gocs.letmeknow.R;

public class RegisterActivity_ViewBinding implements Unbinder {
  private RegisterActivity target;

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target, View source) {
    this.target = target;

    target.editTextUserName = Utils.findRequiredViewAsType(source, R.id.text_register_username, "field 'editTextUserName'", EditText.class);
    target.editTextPassword = Utils.findRequiredViewAsType(source, R.id.text_register_password, "field 'editTextPassword'", EditText.class);
    target.buttonRegister = Utils.findRequiredViewAsType(source, R.id.button_register, "field 'buttonRegister'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editTextUserName = null;
    target.editTextPassword = null;
    target.buttonRegister = null;
  }
}
