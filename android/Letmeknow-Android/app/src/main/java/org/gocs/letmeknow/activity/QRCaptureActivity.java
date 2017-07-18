package org.gocs.letmeknow.activity;

import org.gocs.letmeknow.R;

/**
 * Created by lenovo on 2017/7/18.
 */

public class QRCaptureActivity extends BaseActivity {

    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    /**
     * 选择系统图片Request Code
     */
    public static final int REQUEST_IMAGE = 112;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_qrcode_scan;
    }
}
