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

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.activity.NotificationDetailActivity;
import org.gocs.letmeknow.model.Notification;
import org.gocs.letmeknow.network.RetrofitClient;
import org.gocs.letmeknow.service.NotificationPersistService;
import org.gocs.letmeknow.util.UserManager;
import org.gocs.letmeknow.util.handler.DatabaseErrorHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by dynamicheart on 6/28/2017.
 */

public class NotificationFragment extends BaseFragment {
    @BindView(R.id.recyclerView_notification)
    RecyclerView recyclerViewNoticationList;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    private NotificationRecyclerViewAdapter notificationRecyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this, view);
        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView(){
        recyclerViewNoticationList.setLayoutManager(new LinearLayoutManager(recyclerViewNoticationList.getContext()));
        notificationRecyclerViewAdapter = new NotificationRecyclerViewAdapter(getActivity());
        recyclerViewNoticationList.setAdapter(notificationRecyclerViewAdapter);

        // Setup refresh listener which triggers new data loading
        swipeRefreshLayout.setOnRefreshListener(()->{
            // Your code to refresh the list here.
            // Make sure you call swipeContainer.setRefreshing(false)
            // once the network request has completed successfully.
            NotificationPersistService.listByRecipientId(UserManager.getCurrentUser().getUserId())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(notifications -> {
                        notificationRecyclerViewAdapter.clear();
                        notificationRecyclerViewAdapter.addAll(notifications);

                        // Now we call setRefreshing(false) to signal refresh has finished
                        swipeRefreshLayout.setRefreshing(false);
                    }, throwable -> {
                        swipeRefreshLayout.setRefreshing(false);
                    });

        });

        NotificationPersistService.listByRecipientId(UserManager.getCurrentUser().getUserId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notifications -> {
                    notificationRecyclerViewAdapter.clear();
                    notificationRecyclerViewAdapter.addAll(notifications);
                }, throwable -> {});

    }


    class NotificationRecyclerViewAdapter extends RecyclerView.Adapter<NotificationRecyclerViewAdapter.ViewHolder> {
        private List<Notification> mNotificationList = new ArrayList<>();
        private Context mContext;

        class ViewHolder extends RecyclerView.ViewHolder {

            private final View mView;
            private final TextView mTextViewSenderName;
            private final TextView mTextViewGroupName;
            private final TextView mTextViewContent;

            private ViewHolder(View view) {
                super(view);
                mView = view;
                mTextViewSenderName = (TextView) view.findViewById(R.id.text_sender_name);
                mTextViewGroupName = (TextView) view.findViewById(R.id.text_group_name);
                mTextViewContent = (TextView) view.findViewById(R.id.text_content);
            }

        }

        String getValueAt(int position) {
            return mNotificationList.get(position).getGroupName();
        }

        NotificationRecyclerViewAdapter(Context context, List<Notification> NotificationList) {
            this.mContext = context;
            this.mNotificationList = NotificationList;
        }

        NotificationRecyclerViewAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_card_notification, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mTextViewSenderName.setText(mNotificationList.get(position).getSenderName());
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

        // Clean all elements of the recycler
        void clear() {
            mNotificationList.clear();
            notifyDataSetChanged();
        }

        // Add a list of items -- change to type used
        void addAll(List<Notification> list) {
            mNotificationList.addAll(list);
            notifyDataSetChanged();
        }
    }
    
}
