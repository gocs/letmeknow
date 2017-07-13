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
import org.gocs.letmeknow.activity.CircleInfoActivity;
import org.gocs.letmeknow.application.Constants;
import org.gocs.letmeknow.model.CircleBrief;
import org.gocs.letmeknow.network.RetrofitClient;
import org.gocs.letmeknow.util.handler.NetworkErrorHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dynamicheart on 6/28/2017.
 */

public class CircleFragment extends BaseFragment {
    @BindView(R.id.recyclerview_circle_list)
    RecyclerView recyclerViewCircleList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_circle, container, false);
        ButterKnife.bind(this, view);
        setupRecyclerView();
        return view;
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
                    recyclerViewCircleList.setAdapter(new CircleRecyclerViewAdapter(getActivity(), (List<CircleBrief>) response.getData().get(Constants.JSON_KEY_CIRCLES)));
                }, NetworkErrorHandler.basicErrorHandler);
    }

    private class CircleRecyclerViewAdapter extends RecyclerView.Adapter<CircleRecyclerViewAdapter.ViewHolder>{
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

        CircleRecyclerViewAdapter(Context context, List<CircleBrief> circleBriefList) {
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
                Intent intent = new Intent(mContext, CircleInfoActivity.class);
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return mCircleBriefList.size();
        }
    }
}
