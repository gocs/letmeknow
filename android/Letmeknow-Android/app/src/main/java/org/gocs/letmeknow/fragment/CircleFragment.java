package org.gocs.letmeknow.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.network.RetrofitClient;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    private void setupRecyclerView(){
        RetrofitClient.getService();
        recyclerViewCircleList.setLayoutManager(new LinearLayoutManager(recyclerViewCircleList.getContext()));

    }
}
