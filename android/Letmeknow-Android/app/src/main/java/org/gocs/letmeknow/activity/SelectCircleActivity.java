package org.gocs.letmeknow.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.application.Constants;
import org.gocs.letmeknow.model.CircleBrief;
import org.gocs.letmeknow.network.RetrofitClient;
import org.gocs.letmeknow.util.handler.NetworkErrorHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dynamicheart on 7/18/2017.
 */

public class SelectCircleActivity extends BaseActivity{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview_select_circle_list)
    RecyclerView recyclerViewCircleList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        setupRecyclerView();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_select_circle;
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //set toolbar title
        getSupportActionBar().setTitle(R.string.select_circle);
    }

    @SuppressWarnings("unchecked")
    private void setupRecyclerView(){
        RetrofitClient.getService()
                .getCircles()
                .flatMap(NetworkErrorHandler.ErrorFilter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response->{
                    recyclerViewCircleList.setLayoutManager(new LinearLayoutManager(recyclerViewCircleList.getContext()));
                    recyclerViewCircleList.setAdapter(new SelectCircleRecyclerViewAdapter(this, (List<CircleBrief>) response.getData().get(Constants.JSON_KEY_CIRCLES)));
                }, NetworkErrorHandler.basicErrorHandler);
    }


    private class SelectCircleRecyclerViewAdapter extends RecyclerView.Adapter<SelectCircleRecyclerViewAdapter.ViewHolder>{
        private List<CircleBrief> mCircleBriefList = new ArrayList<>();
        private Context mContext;

        class ViewHolder extends RecyclerView.ViewHolder {

            private final View mView;
            private final TextView mTextView;

            private ViewHolder(View view) {
                super(view);
                mView = view;
                mTextView = (TextView) view.findViewById(android.R.id.text1);
            }

        }

        String getValueAt(int position) {
            return mCircleBriefList.get(position).getGroupName();
        }

        SelectCircleRecyclerViewAdapter(Context context, List<CircleBrief> circleBriefList) {
            this.mContext = context;
            this.mCircleBriefList = circleBriefList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mTextView.setText(mCircleBriefList.get(position).getGroupName());
            holder.mView.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, SelectMemberActivity.class);
                intent.putExtra(SelectMemberActivity.GROUP_ID, mCircleBriefList.get(position).getGroupId());
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return mCircleBriefList.size();
        }
    }
}
