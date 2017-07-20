package org.gocs.letmeknow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMConversationQuery;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.Conversation;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationQueryCallback;
import com.avos.avoscloud.im.v2.callback.AVIMMessagesQueryCallback;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.application.Constants;
import org.gocs.letmeknow.model.Member;
import org.gocs.letmeknow.util.NotificationBoxUtils;
import org.gocs.letmeknow.util.event.ImTypeMessageEvent;
import org.gocs.letmeknow.util.event.ImTypeMessageResendEvent;
import org.gocs.letmeknow.util.event.InputBottomBarTextEvent;
import org.gocs.letmeknow.util.manager.leancloud.AVImClientManager;
import org.gocs.letmeknow.widget.AVCommonViewHolder;
import org.gocs.letmeknow.widget.AVInputBottomBar;
import org.gocs.letmeknow.widget.LeftTextHolder;
import org.gocs.letmeknow.widget.RightTextHolder;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by dynaicheart on 7/20/2017.
 */

public class ConversationActivity extends BaseActivity {

    public static final String MEMBER_SERIALIZABLE = "MEMBER_SERIALIZABLE";

    AVIMConversation imConversation;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    MultipleItemAdapter itemAdapter;

    @BindView(R.id.recyclerview_chat)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_container_chat)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.inputbottombar_chat)
    AVInputBottomBar inputBottomBar;

    protected LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String memberId = getIntent().getStringExtra(Constants.MEMBER_ID);
        initToolbar(memberId);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        itemAdapter = new MultipleItemAdapter();
        recyclerView.setAdapter(itemAdapter);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                AVIMMessage message = itemAdapter.getFirstMessage();
                imConversation.queryMessages(message.getMessageId(), message.getTimestamp(), 20, new AVIMMessagesQueryCallback() {
                    @Override
                    public void done(List<AVIMMessage> list, AVIMException e) {
                        refreshLayout.setRefreshing(false);
                        if (filterException(e)) {
                            if (null != list && list.size() > 0) {
                                itemAdapter.addMessageList(list);
                                itemAdapter.notifyDataSetChanged();

                                layoutManager.scrollToPositionWithOffset(list.size() - 1, 0);
                            }
                        }
                    }
                });
            }
        });
        getConversation(memberId);
    }

    private void getConversation(final String memberId) {
        final AVIMClient client = AVImClientManager.getInstance().getClient();
        AVIMConversationQuery conversationQuery = client.getQuery();
        conversationQuery.withMembers(Arrays.asList(memberId), true);
        conversationQuery.whereEqualTo("customConversationType", 1);
        conversationQuery.findInBackground(new AVIMConversationQueryCallback() {
            @Override
            public void done(List<AVIMConversation> list, AVIMException e) {
                if (filterException(e)) {
                    //注意：此处仍有漏洞，如果获取了多个 conversation，默认取第一个
                    if (null != list && list.size() > 0) {
                        setConversation(list.get(0));
                    } else {
                        HashMap<String, Object> attributes = new HashMap<String, Object>();
                        attributes.put("customConversationType", 1);
                        client.createConversation(Arrays.asList(memberId), null, attributes, false, new AVIMConversationCreatedCallback() {
                            @Override
                            public void done(AVIMConversation avimConversation, AVIMException e) {
                                setConversation(avimConversation);
                            }
                        });
                    }
                }
            }
        });
    }

    public void setConversation(AVIMConversation conversation) {
        imConversation = conversation;
        refreshLayout.setEnabled(true);
        inputBottomBar.setTag(imConversation.getConversationId());
        fetchMessages();
        NotificationBoxUtils.addTag(conversation.getConversationId());
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_conversation;
    }

    private void initToolbar(String memberId) {
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //set toolbar title
        getSupportActionBar().setTitle(memberId);
    }


    private class MultipleItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private final int ITEM_LEFT_TEXT = 0;
        private final int ITEM_RIGHT_TEXT = 1;

        // 时间间隔最小为十分钟
        private final long TIME_INTERVAL = 10 * 60 * 1000;

        private List<AVIMMessage> messageList = new ArrayList<AVIMMessage>();

        public MultipleItemAdapter() {
        }

        public void setMessageList(List<AVIMMessage> messages) {
            messageList.clear();
            if (null != messages) {
                messageList.addAll(messages);
            }
        }

        public void addMessageList(List<AVIMMessage> messages) {
            messageList.addAll(0, messages);
        }

        public void addMessage(AVIMMessage message) {
            messageList.addAll(Arrays.asList(message));
        }

        public AVIMMessage getFirstMessage() {
            if (null != messageList && messageList.size() > 0) {
                return messageList.get(0);
            } else {
                return null;
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == ITEM_LEFT_TEXT) {
                return new LeftTextHolder(parent.getContext(), parent);
            } else if (viewType == ITEM_RIGHT_TEXT) {
                return new RightTextHolder(parent.getContext(), parent);
            } else {
                //TODO
                return null;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((AVCommonViewHolder) holder).bindData(messageList.get(position));
            if (holder instanceof LeftTextHolder) {
                ((LeftTextHolder) holder).showTimeView(shouldShowTime(position));
            } else if (holder instanceof RightTextHolder) {
                ((RightTextHolder) holder).showTimeView(shouldShowTime(position));
            }
        }

        @Override
        public int getItemViewType(int position) {
            AVIMMessage message = messageList.get(position);
            if (message.getFrom().equals(AVImClientManager.getInstance().getClientId())) {
                return ITEM_RIGHT_TEXT;
            } else {
                return ITEM_LEFT_TEXT;
            }
        }

        @Override
        public int getItemCount() {
            return messageList.size();
        }

        private boolean shouldShowTime(int position) {
            if (position == 0) {
                return true;
            }
            long lastTime = messageList.get(position - 1).getTimestamp();
            long curTime = messageList.get(position).getTimestamp();
            return curTime - lastTime > TIME_INTERVAL;
        }

    }

    /**
     * 拉取消息，必须加入 conversation 后才能拉取消息
     */
    private void fetchMessages() {
        imConversation.queryMessages(new AVIMMessagesQueryCallback() {
            @Override
            public void done(List<AVIMMessage> list, AVIMException e) {
                if (filterException(e)) {
                    itemAdapter.setMessageList(list);
                    recyclerView.setAdapter(itemAdapter);
                    itemAdapter.notifyDataSetChanged();
                    scrollToBottom();
                }
            }
        });
    }

    /**
     * 输入事件处理，接收后构造成 AVIMTextMessage 然后发送
     * 因为不排除某些特殊情况会受到其他页面过来的无效消息，所以此处加了 tag 判断
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(InputBottomBarTextEvent textEvent) {
        if (null != imConversation && null != textEvent) {
            if (!TextUtils.isEmpty(textEvent.sendContent) && imConversation.getConversationId().equals(textEvent.tag)) {
                AVIMTextMessage message = new AVIMTextMessage();
                message.setText(textEvent.sendContent);
                itemAdapter.addMessage(message);
                itemAdapter.notifyDataSetChanged();
                scrollToBottom();
                imConversation.sendMessage(message, new AVIMConversationCallback() {
                    @Override
                    public void done(AVIMException e) {
                        itemAdapter.notifyDataSetChanged();
                    }
                });
            }
        }
    }

    /**
     * 处理推送过来的消息
     * 同理，避免无效消息，此处加了 conversation id 判断
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ImTypeMessageEvent event) {
        if (null != imConversation && null != event &&
                imConversation.getConversationId().equals(event.conversation.getConversationId())) {
            itemAdapter.addMessage(event.message);
            itemAdapter.notifyDataSetChanged();
            scrollToBottom();
        }
    }

    /**
     * 重新发送已经发送失败的消息
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ImTypeMessageResendEvent event) {
        if (null != imConversation && null != event) {
            if (AVIMMessage.AVIMMessageStatus.AVIMMessageStatusFailed == event.message.getMessageStatus()
                    && imConversation.getConversationId().equals(event.message.getConversationId())) {
                imConversation.sendMessage(event.message, new AVIMConversationCallback() {
                    @Override
                    public void done(AVIMException e) {
                        itemAdapter.notifyDataSetChanged();
                    }
                });
                itemAdapter.notifyDataSetChanged();
            }
        }
    }

    private void scrollToBottom() {
        layoutManager.scrollToPositionWithOffset(itemAdapter.getItemCount() - 1, 0);
    }

    protected boolean filterException(Exception e) {
        if (e != null) {
            e.printStackTrace();
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle extras = intent.getExtras();
        if (null != extras && extras.containsKey(Constants.MEMBER_ID)) {
            String memberId = extras.getString(Constants.MEMBER_ID);
            setTitle(memberId);
            getConversation(memberId);
        }
    }
}
