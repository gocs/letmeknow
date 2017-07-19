package org.gocs.letmeknow.fragment;

import android.media.Image;
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
import org.gocs.letmeknow.model.CircleBrief;
import org.gocs.letmeknow.model.Member;
import org.gocs.letmeknow.model.PrivateMessage;
import org.gocs.letmeknow.network.RetrofitClient;
import org.gocs.letmeknow.util.PicassoImgUtil;
import org.gocs.letmeknow.util.handler.NetworkErrorHandler;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static org.gocs.letmeknow.R.id.item_cm_avatar;
import static org.gocs.letmeknow.R.id.item_cm_name;
import static org.gocs.letmeknow.activity.CircleInfoActivity.CIRCLE_SERIALIZABLE;

/**
 * Created by lenovo on 2017/6/30.
 */

public class CircleInformerFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private CiAdapter mAdapter;
    private List<Member> ciList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_circle_info_informer, container, false);
        ButterKnife.bind(this, view);

        setupRecyclerView(view);
        //initData();

        return view;
    }

    private void setupRecyclerView(View view){
        CircleBrief circleBrief = (CircleBrief)(getActivity().getIntent().getSerializableExtra(CIRCLE_SERIALIZABLE));
        RetrofitClient.getService()
                .getCircleInformers(circleBrief.getGroupId())
                .flatMap(NetworkErrorHandler.ErrorFilter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response->{
                    ciList = (List<Member>) response.getData().get("groupNotifier");
                    mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerview_ci_list);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mAdapter = new CiAdapter(ciList);
                    mRecyclerView.setAdapter(mAdapter);
                }, NetworkErrorHandler.basicErrorHandler);
    }

    protected void initData(){
        ciList = new ArrayList<Member>();
        //TODO fill the list with response data.

        /******************  test code  *****************/
        Member member1 = new Member();
        member1.setUserName("RongChen");
        Member member2 = new Member();
        member2.setUserName("SSJ");
        ciList.add(member1);
        ciList.add(member2);
    }

    private class CiHolder extends RecyclerView.ViewHolder{
        public ImageView mCiAvatar;
        public TextView mCiName;

        public CiHolder(View view){
            super(view);
            mCiAvatar = (ImageView)view.findViewById(item_cm_avatar);
            mCiName = (TextView) view.findViewById(item_cm_name);
        }
    }

    private class CiAdapter extends RecyclerView.Adapter<CiHolder>{
        private List<Member> mCIs;

        public CiAdapter(List<Member> cis){mCIs = cis;}

        @Override
        public CiHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.item_member_briefcard, parent, false);
            return new CiHolder(view);
        }

        @Override
        public void onBindViewHolder(CircleInformerFragment.CiHolder holder, int position){
            Member ci = ciList.get(position);
            String avatarUrl = ci.getAvatar();
            PicassoImgUtil.loadImgByRawUrl(getActivity(),avatarUrl,holder.mCiAvatar);
            holder.mCiName.setText(ci.getUserName());
        }

        @Override
        public int getItemCount(){
            return ciList.size();
        }
    }
}
