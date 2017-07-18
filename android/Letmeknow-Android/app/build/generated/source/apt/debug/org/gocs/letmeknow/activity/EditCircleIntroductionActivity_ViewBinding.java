// Generated code from Butter Knife. Do not modify!
package org.gocs.letmeknow.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.gocs.letmeknow.R;

public class EditCircleIntroductionActivity_ViewBinding implements Unbinder {
  private EditCircleIntroductionActivity target;

  @UiThread
  public EditCircleIntroductionActivity_ViewBinding(EditCircleIntroductionActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditCircleIntroductionActivity_ViewBinding(EditCircleIntroductionActivity target,
      View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.editText = Utils.findRequiredViewAsType(source, R.id.input_circle_introduction, "field 'editText'", EditText.class);
    target.button = Utils.findRequiredViewAsType(source, R.id.button_edit_circle_introduction, "field 'button'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EditCircleIntroductionActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.editText = null;
    target.button = null;
  }
}
