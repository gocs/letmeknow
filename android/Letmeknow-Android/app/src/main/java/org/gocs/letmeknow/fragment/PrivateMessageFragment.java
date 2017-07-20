package org.gocs.letmeknow.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMConversationsQuery;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.Conversation;
import com.avos.avoscloud.im.v2.callback.AVIMConversationQueryCallback;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.activity.ConversationActivity;
import org.gocs.letmeknow.application.Constants;
import org.gocs.letmeknow.service.NotificationPersistService;
import org.gocs.letmeknow.util.manager.cache.UserManager;
import org.gocs.letmeknow.util.manager.leancloud.AVImClientManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by dynamicheart on 6/28/2017.
 */

public class PrivateMessageFragment extends BaseFragment {
    @BindView(R.id.recyclerView_private_message)
    RecyclerView recyclerViewPrivateMessageList;
    @BindView(R.id.swipe_container_private_message)
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_private_message, container, false);
        ButterKnife.bind(this, view);
        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView(){
        recyclerViewPrivateMessageList.setLayoutManager(new LinearLayoutManager(getContext()));
        PrivateMessageRecyclerViewAdapter privateMessageRecyclerViewAdapter = new PrivateMessageRecyclerViewAdapter(getActivity());
        recyclerViewPrivateMessageList.setAdapter(privateMessageRecyclerViewAdapter);

        swipeRefreshLayout.setOnRefreshListener(()->{
            AVIMConversationsQuery query = AVImClientManager.getInstance().getClient().getConversationsQuery();
            query.findInBackground(new AVIMConversationQueryCallback() {
                @Override
                public void done(List<AVIMConversation> list, AVIMException e) {
                    if(e == null){
                        privateMessageRecyclerViewAdapter.clear();
                        privateMessageRecyclerViewAdapter.addAll(list);
                    }
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        });

        AVIMConversationsQuery query = AVImClientManager.getInstance().getClient().getConversationsQuery();
        query.findInBackground(new AVIMConversationQueryCallback() {
            @Override
            public void done(List<AVIMConversation> list, AVIMException e) {
                if(e == null){
                    privateMessageRecyclerViewAdapter.clear();
                    privateMessageRecyclerViewAdapter.addAll(list);
                }
            }
        });

    }

    class PrivateMessageRecyclerViewAdapter extends RecyclerView.Adapter<PrivateMessageRecyclerViewAdapter.ViewHolder> {
        private List<AVIMConversation> mConversationList = new ArrayList<>();
        private Context mContext;

        class ViewHolder extends RecyclerView.ViewHolder {

            private final View mView;
            private final TextView mTextViewSenderId;
            private final TextView mTextViewTime;
            private final TextView mTextViewLastMessage;

            private ViewHolder(View view) {
                super(view);
                mView = view;
                mTextViewSenderId = (TextView) view.findViewById(R.id.item_private_message_text_member_id);
                mTextViewTime = (TextView) view.findViewById(R.id.item_private_message_text_time);
                mTextViewLastMessage = (TextView) view.findViewById(R.id.item_private_message_text_last_message);
            }

        }

        PrivateMessageRecyclerViewAdapter(Context context, List<AVIMConversation> conversationList) {
            this.mContext = context;
            this.mConversationList = conversationList;
        }

        PrivateMessageRecyclerViewAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_private_message, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            //holder.mTextViewSenderId.setText(mConversationList.get(position).getLastMessage().getFrom());
            holder.mView.setOnClickListener(v -> {
                //Intent intent = new Intent(mContext, ConversationActivity.class);
                //intent.putExtra(Constants.MEMBER_ID, mConversationList.get(position).getLastMessage().getFrom());
                //mContext.startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return mConversationList.size();
        }

        // Clean all elements of the recycler
        void clear() {
            mConversationList.clear();
            notifyDataSetChanged();
        }

        // Add a list of items -- change to type used
        void addAll(List<AVIMConversation> list) {
            mConversationList.addAll(list);
            notifyDataSetChanged();
        }
    }
}
