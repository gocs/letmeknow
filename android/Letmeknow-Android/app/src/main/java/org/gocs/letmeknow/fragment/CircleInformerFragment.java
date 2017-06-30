package org.gocs.letmeknow.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.gocs.letmeknow.R;

import butterknife.ButterKnife;

/**
 * Created by lenovo on 2017/6/30.
 */

public class CircleInformerFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_circle_info_informer, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
