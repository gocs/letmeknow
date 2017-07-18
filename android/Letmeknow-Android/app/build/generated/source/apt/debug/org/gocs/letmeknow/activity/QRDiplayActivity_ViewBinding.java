// Generated code from Butter Knife. Do not modify!
package org.gocs.letmeknow.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.gocs.letmeknow.R;

public class QRDiplayActivity_ViewBinding implements Unbinder {
  private QRDiplayActivity target;

  @UiThread
  public QRDiplayActivity_ViewBinding(QRDiplayActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public QRDiplayActivity_ViewBinding(QRDiplayActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.circleTitle = Utils.findRequiredViewAsType(source, R.id.text_circle_title, "field 'circleTitle'", TextView.class);
    target.qrCode = Utils.findRequiredViewAsType(source, R.id.img_circle_qrcode, "field 'qrCode'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    QRDiplayActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.circleTitle = null;
    target.qrCode = null;
  }
}
