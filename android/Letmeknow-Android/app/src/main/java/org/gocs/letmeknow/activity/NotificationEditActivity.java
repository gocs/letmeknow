package org.gocs.letmeknow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVPush;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.SendCallback;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.couchbase.DataBaseClient;
import org.gocs.letmeknow.model.Circle;
import org.gocs.letmeknow.model.CircleBrief;
import org.gocs.letmeknow.model.Member;
import org.gocs.letmeknow.model.Notification;
import org.gocs.letmeknow.model.component.NotificationType;
import org.gocs.letmeknow.model.component.Receipt;
import org.gocs.letmeknow.service.NotificationPersistService;
import org.gocs.letmeknow.util.ToastUtils;
import org.gocs.letmeknow.util.UserManager;
import org.gocs.letmeknow.util.handler.DatabaseErrorHandler;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by dynamicheart on 7/7/2017.
 */

public class NotificationEditActivity extends BaseActivity{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edit_text_notification_edit)
    EditText editTextNotificationEdit;

    private ArrayList<Member> memberList;

    public static final String MEMBER_LIST_SERIALIZABLE = "MEMBER_LIST_SERIALIZABLE";

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        memberList = (ArrayList<Member>) intent.getSerializableExtra(MEMBER_LIST_SERIALIZABLE);
        initToolbar();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_notification_edit;
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //set toolbar title
        getSupportActionBar().setTitle(R.string.edit_titile);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_notification_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_send_notification:
                if(memberList == null || memberList.size() == 0){
                    return true;
                }
                sendNotification();
                break;
            case R.id.menu_select_circle:
                Intent intent = new Intent(NotificationEditActivity.this, SelectCircleActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sendNotification(){
        CircleBrief circleBrief = (CircleBrief) getIntent().getSerializableExtra(SelectMemberActivity.GROUP_BRIEF);

        Notification notification = new Notification();
        notification.setGroupId(circleBrief.getGroupId());
        notification.setGroupName(circleBrief.getGroupName());
        notification.setSenderId(UserManager.getCurrentUser().getUserId());
        notification.setSenderName(UserManager.getCurrentUser().getUserName());
        notification.setContent(editTextNotificationEdit.getText().toString());
        notification.setNotificationType(NotificationType.NORMAL);

        Map<String,Receipt> receipts = new HashMap<>();
        for(Member member: memberList){
            Receipt receipt = new Receipt();
            receipt.setRecipientName(member.getUserName());
            receipts.put(member.getUserId(), receipt);
        }
        notification.setReceiptMap(receipts);

        //set up channels
        List<String> channels = new LinkedList<String>();
        channels.add(notification.getSenderId());
        for(String recipientId:notification.getReceiptMap().keySet()){
            channels.add(recipientId);
        }

        NotificationPersistService.create(notification)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notificationId->{
                    AVPush push = new AVPush();

                    AVQuery<AVInstallation> query = AVInstallation.getQuery();

                    query.whereContainedIn("installationId", channels);
                    push.setQuery(query);

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("action", "com.gocs.letmeknow.action.NOTIFICATION");

                    push.setData(jsonObject);
                    push.setPushToAndroid(true);
                    push.sendInBackground(new SendCallback() {
                        @Override
                        public void done(AVException e) {
                            ToastUtils.showShortToast("send successfully");
                        }
                    });
                }, DatabaseErrorHandler.basicErrorHandler);


    }
}
