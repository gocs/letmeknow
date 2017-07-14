package org.gocs.letmeknow.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.activity.NotificationDetailActivity;
import org.gocs.letmeknow.model.Notification;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dynamicheart on 7/14/2017.
 */

public class SendRecyclerViewAdapter  extends RecyclerView.Adapter<SendRecyclerViewAdapter.ViewHolder> {
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

    public SendRecyclerViewAdapter(Context context, List<Notification> NotificationList) {
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
