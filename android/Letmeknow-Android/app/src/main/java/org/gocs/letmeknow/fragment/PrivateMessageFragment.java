package org.gocs.letmeknow.fragment;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.model.remote.PrivateMessage;

import butterknife.ButterKnife;

import static org.gocs.letmeknow.R.id.item_pm_content;
import static org.gocs.letmeknow.R.id.item_pm_img;
import static org.gocs.letmeknow.R.id.item_pm_time;
import static org.gocs.letmeknow.R.id.item_pm_title;

/**
 * Created by dynamicheart on 6/28/2017.
 */

public class PrivateMessageFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private PmAdapter mAdapter;
    private List<PrivateMessage> pmList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_private_msglist, container, false);
        ButterKnife.bind(this, view);

        initData();
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerview_pm_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new PmAdapter(pmList);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    protected void initData(){
        pmList = new ArrayList<PrivateMessage>();
        PrivateMessage pm1 = new PrivateMessage();
        PrivateMessage pm2 = new PrivateMessage();
        PrivateMessage pm3 = new PrivateMessage();
        pm1.setSenderUserName("Jerry");
        pm2.setSenderUserName("Tom");
        pmList.add(pm1);
        pmList.add(pm2);
        pmList.add(pm3);
    }

    private class PmHolder extends RecyclerView.ViewHolder{
        public TextView mPmTitle;
        public TextView mPmContent;
        public ImageView mPmImg;
        public TextView mPmTime;

        public PmHolder(View view){
            super(view);
            mPmTitle = (TextView) view.findViewById(item_pm_title);
            mPmImg = (ImageView) view.findViewById(item_pm_img);
            mPmContent = (TextView) view.findViewById(item_pm_content);
            mPmTime = (TextView) view.findViewById(item_pm_time);
        }
    }

    private class PmAdapter extends RecyclerView.Adapter<PmHolder>{
        private List<PrivateMessage> mPMs;

        public PmAdapter(List<PrivateMessage> pms){
            mPMs = pms;
        }

        @Override
        public PmHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.item_private_msg, parent, false);
            return new PmHolder(view);
        }

        @Override
        public void onBindViewHolder(PmHolder holder, int position){
            PrivateMessage pm = pmList.get(position);
            holder.mPmTitle.setText(pm.getSenderUserName());
            holder.mPmContent.setText(pm.getContent());
            holder.mPmTime.setText(pm.getSendTime().toString());
        }

        @Override
        public int getItemCount(){
            return pmList.size();
        }

    }


}
