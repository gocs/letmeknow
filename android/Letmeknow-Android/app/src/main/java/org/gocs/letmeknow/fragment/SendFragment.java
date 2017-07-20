package org.gocs.letmeknow.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.activity.NotificationDetailActivity;
import org.gocs.letmeknow.model.Notification;
import org.gocs.letmeknow.service.NotificationPersistService;
import org.gocs.letmeknow.util.manager.cache.UserManager;
import org.gocs.letmeknow.util.handler.DatabaseErrorHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by dynamicheart on 6/28/2017.
 */

public class SendFragment extends BaseFragment {

    @BindView(R.id.recyclerView_send)
    RecyclerView recyclerViewSendList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_send, container, false);
        ButterKnife.bind(this, view);
        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView(){
        NotificationPersistService.listBySenderId(UserManager.getCurrentUser().getUserId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notifications -> {
                    recyclerViewSendList.setLayoutManager(new LinearLayoutManager(recyclerViewSendList.getContext()));
                    recyclerViewSendList.setAdapter(new SendRecyclerViewAdapter(getActivity(), notifications));
                }, DatabaseErrorHandler.basicErrorHandler);
    }

    private class SendRecyclerViewAdapter  extends RecyclerView.Adapter<SendRecyclerViewAdapter.ViewHolder> {
        private List<Notification> mNotificationList = new ArrayList<>();
        private Context mContext;

        class ViewHolder extends RecyclerView.ViewHolder {

            private final View mView;
            private final TextView mTextViewGroupName;
            private final TextView mTextViewContent;

            private ViewHolder(View view) {
                super(view);
                mView = view;
                mTextViewGroupName = (TextView) view.findViewById(R.id.text_group_name);
                mTextViewContent = (TextView) view.findViewById(R.id.text_content);
            }

        }

        String getValueAt(int position) {
            return mNotificationList.get(position).getGroupName();
        }

        SendRecyclerViewAdapter(Context context, List<Notification> NotificationList) {
            this.mContext = context;
            this.mNotificationList = NotificationList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_card_notification, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mTextViewGroupName.setText(mNotificationList.get(position).getGroupName());
            holder.mTextViewContent.setText(mNotificationList.get(position).getContent());
            holder.mView.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, NotificationDetailActivity.class);
                intent.putExtra(NotificationDetailActivity.NOTIFICATION_SERIALIZABLE, mNotificationList.get(position));
                mContext.startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return mNotificationList.size();
        }
    }
}
