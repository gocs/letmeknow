package org.gocs.letmeknow.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.model.Circle;
import org.gocs.letmeknow.model.CircleBrief;
import org.gocs.letmeknow.network.RetrofitClient;
import org.gocs.letmeknow.util.handler.NetworkErrorHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static org.gocs.letmeknow.activity.CircleInfoActivity.CIRCLE_SERIALIZABLE;

/**
 * Created by lenovo on 2017/6/30.
 */

public class CircleIntroductionFragment extends BaseFragment {

    @BindView(R.id.text_circle_introduction)
    TextView introTV;

    @BindView(R.id.text_circle_owner)
    TextView masterTV;

    public static final String CIRCLE_DETAIL_SERIALIZABLE = "CIRCLE_DETAIL_SERIALIZABLE";

    private Circle circleDetail;
    //private CircleBrief circleBrief;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_circle_info_introduction, container, false);
        ButterKnife.bind(this, view);

        Intent intent = this.getActivity().getIntent();
        //circleBrief = (CircleBrief)intent.getSerializableExtra(CIRCLE_SERIALIZABLE);
        circleDetail = (Circle)this.getActivity().getIntent().getSerializableExtra(CIRCLE_DETAIL_SERIALIZABLE);
        introTV.setText(circleDetail.getGroupIntroduction());
        masterTV.setText(circleDetail.getMaster());

        return view;
    }

}
