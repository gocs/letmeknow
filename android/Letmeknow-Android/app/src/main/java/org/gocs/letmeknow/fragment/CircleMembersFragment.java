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
import org.gocs.letmeknow.application.Constants;
import org.gocs.letmeknow.model.CircleBrief;
import org.gocs.letmeknow.model.Member;
import org.gocs.letmeknow.model.PrivateMessage;
import org.gocs.letmeknow.network.RetrofitClient;
import org.gocs.letmeknow.model.CircleBrief;
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

public class CircleMembersFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private CmAdapter mAdapter;
    private List<Member> cmList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_circle_info_members, container, false);
        ButterKnife.bind(this, view);

        setupRecyclerView(view);

        //initData();

        return view;
    }

    private void setupRecyclerView(View view){
        CircleBrief circleBrief = (CircleBrief)(getActivity().getIntent().getSerializableExtra(CIRCLE_SERIALIZABLE));
        RetrofitClient.getService()
                .getCircleMembers(circleBrief.getGroupId())
                .flatMap(NetworkErrorHandler.ErrorFilter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response->{
                    cmList = (List<Member>) response.getData().get("groupMember");
                    mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerview_cm_list);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mAdapter = new CmAdapter(cmList);
                    mRecyclerView.setAdapter(mAdapter);
                }, NetworkErrorHandler.basicErrorHandler);
    }

    protected void initData(){
        cmList = new ArrayList<Member>();
        //TODO fill the list with response data.

        /******************  test code  *****************/
        Member member1 = new Member();
        member1.setUserName("RongChen");
        Member member2 = new Member();
        member2.setUserName("SSJ");
        Member member3 = new Member();
        member3.setUserName("SBJ");
        cmList.add(member1);
        cmList.add(member2);
        cmList.add(member3);
    }

    private class CmHolder extends RecyclerView.ViewHolder{
        public ImageView mCmAvatar;
        public TextView mCmName;

        public CmHolder(View view){
            super(view);
            mCmAvatar = (ImageView)view.findViewById(item_cm_avatar);
            mCmName = (TextView) view.findViewById(item_cm_name);
        }
    }

    private class CmAdapter extends RecyclerView.Adapter<CmHolder>{
        private List<Member> mCMs;

        public CmAdapter(List<Member> cms){mCMs = cms;}

        @Override
        public CmHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.item_member_briefcard, parent, false);
            return new CmHolder(view);
        }

        @Override
        public void onBindViewHolder(CircleMembersFragment.CmHolder holder, int position){
            Member cm = cmList.get(position);
            //holder.mCmAvatar.setImageDrawable(cm.getAvatar());
            //TODO 图像如何取回并加载？ 使用picaso？ 是否需要在Member类中加入新的属性？
            holder.mCmName.setText(cm.getUserName());
        }

        @Override
        public int getItemCount(){
            return cmList.size();
        }
    }
}
