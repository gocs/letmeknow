package org.gocs.letmeknow.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.deser.Deserializers;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.model.CircleBrief;
import org.gocs.letmeknow.util.QRCodeUtils;

import butterknife.BindView;

import static org.gocs.letmeknow.util.QRCodeUtils.encodeQR;

/**
 * Created by lenovo on 2017/7/18.
 */

public class QRDiplayActivity extends BaseActivity {

    public static final String CIRCLE_SERIALIZABLE = "CIRCLE_SERIALIZABLE";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.text_circle_title)
    TextView circleTitle;

    @BindView(R.id.img_circle_qrcode)
    ImageView qrCode;

    private CircleBrief circleBrief;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        circleBrief = (CircleBrief)getIntent().getSerializableExtra(CIRCLE_SERIALIZABLE);
        String qrStr = encodeQR(circleBrief.getGroupId());
        Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        Bitmap bitmap= EncodingUtils.createQRCode(qrStr, 1000, 1000, logoBitmap);
        circleTitle.setText(circleBrief.getGroupName());
        qrCode.setImageBitmap(bitmap);

        initToolbar();

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_qrcode_display;
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //set toolbar title
        getSupportActionBar().setTitle(R.string.circle_title_join);
    }


}
