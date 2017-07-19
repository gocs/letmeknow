package org.gocs.letmeknow.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.application.App;
import org.gocs.letmeknow.model.User;
import org.gocs.letmeknow.network.OkHttpProvider;
import org.gocs.letmeknow.network.RetrofitClient;
import org.gocs.letmeknow.util.PicassoImgUtil;
import org.gocs.letmeknow.util.ToastUtils;
import org.gocs.letmeknow.util.event.UserLogoutEvent;
import org.gocs.letmeknow.util.handler.NetworkErrorHandler;
import org.gocs.letmeknow.util.UserManager;
import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static org.gocs.letmeknow.application.Constants.RESULT_LOAD_IMAGE;
import static org.gocs.letmeknow.util.PicassoImgUtil.loadImgByInternetUrl;
import static org.gocs.letmeknow.util.PicassoImgUtil.loadImgByRawUrl;

/**
 * Created by dynamicheart on 6/30/2017.
 */

public class UserProfileActivity extends BaseActivity {

    private final static float SCALED_IMG_SIZE = 200;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.button_logoff)
    Button button;

    @BindView(R.id.layout_user_username)
    RelativeLayout layout_username;

    @BindView(R.id.layout_user_name)
    RelativeLayout layout_name;

    @BindView(R.id.layout_user_phone)
    RelativeLayout layout_phone;

    @BindView(R.id.layout_user_email)
    RelativeLayout layout_email;

    @BindView(R.id.text_user_username)
    TextView text_username;

    @BindView(R.id.text_user_name)
    TextView text_name;

    @BindView(R.id.text_user_phone)
    TextView text_phone;

    @BindView(R.id.text_user_email)
    TextView text_email;

    @BindView(R.id.image_user_portrait)
    ImageView image_avatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        initData();
        button.setOnClickListener(view -> {
            Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);


            RetrofitClient.getService().logout()
                    .flatMap(NetworkErrorHandler.ErrorFilter)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        EventBus.getDefault().post(new UserLogoutEvent());
                    }, NetworkErrorHandler.basicErrorHandler);

            UserManager.changeLoginStatus(false);
            OkHttpProvider.clearCookie();
        });
        image_avatar.setOnClickListener(view->{
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            galleryIntent.setType("image/*");
            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null !=data){
            Uri selectedImageUri = data.getData();
            Bitmap bitmap = null;
            try{
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
            }catch(IOException ignore){
                ToastUtils.showShortToast("读取图片失败");
                return;
            }
            float ratio = Math.min(
                    (float) SCALED_IMG_SIZE / bitmap.getWidth(),
                    (float) SCALED_IMG_SIZE / bitmap.getHeight());
            int width = Math.round((float) ratio * bitmap.getWidth());
            int height = Math.round((float) ratio * bitmap.getHeight());

            bitmap = Bitmap.createScaledBitmap(bitmap, width,
                    height, true);
            image_avatar.setImageBitmap(bitmap);
            //TODO send new avatar to server.
            ToastUtils.showShortToast("成功更改头像");
        } else {
            ToastUtils.showShortToast("未选择图片");
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_profile;
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //set toolbar title
        getSupportActionBar().setTitle(R.string.user_info_title);
    }

    private void initData(){
        User user = UserManager.getCurrentUser();
        text_username.setText(user.getUserName());
        text_phone.setText(user.getPhoneNumber());
        text_email.setText(user.getEmail());
        String rawUrl = UserManager.getCurrentUser().getAvatarUrl();
        loadImgByRawUrl(this,rawUrl,image_avatar);
    }
}
