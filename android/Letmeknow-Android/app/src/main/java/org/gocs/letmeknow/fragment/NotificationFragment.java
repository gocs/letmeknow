package org.gocs.letmeknow.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.adapter.NotificationRecyclerViewAdapter;
import org.gocs.letmeknow.network.RetrofitClient;
import org.gocs.letmeknow.service.NotificationService;
import org.gocs.letmeknow.util.UserManager;
import org.gocs.letmeknow.util.handler.DatabaseErrorHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by dynamicheart on 6/28/2017.
 */

public class NotificationFragment extends BaseFragment {
    @BindView(R.id.recyclerView_notification)
    RecyclerView recyclerViewNoticationList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this, view);
        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView(){
        NotificationService.listByRecipientId(UserManager.getCurrentUser().getUserId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notifications -> {
                    recyclerViewNoticationList.setLayoutManager(new LinearLayoutManager(recyclerViewNoticationList.getContext()));
                    recyclerViewNoticationList.setAdapter(new NotificationRecyclerViewAdapter(getActivity(), notifications));
                }, DatabaseErrorHandler.basicErrorHandler);
    }

    
}
