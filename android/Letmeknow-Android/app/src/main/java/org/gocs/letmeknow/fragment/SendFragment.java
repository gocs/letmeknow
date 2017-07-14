package org.gocs.letmeknow.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.adapter.SendRecyclerViewAdapter;
import org.gocs.letmeknow.service.NotificationService;
import org.gocs.letmeknow.util.UserManager;
import org.gocs.letmeknow.util.handler.DatabaseErrorHandler;

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
        NotificationService.listBySenderId(UserManager.getCurrentUser().getUserId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notifications -> {
                    recyclerViewSendList.setLayoutManager(new LinearLayoutManager(recyclerViewSendList.getContext()));
                    recyclerViewSendList.setAdapter(new SendRecyclerViewAdapter(getActivity(), notifications));
                }, DatabaseErrorHandler.basicErrorHandler);
    }
}
