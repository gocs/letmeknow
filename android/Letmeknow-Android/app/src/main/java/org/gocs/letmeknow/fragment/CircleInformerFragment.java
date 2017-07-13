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
import org.gocs.letmeknow.model.Member;
import org.gocs.letmeknow.model.PrivateMessage;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

import static org.gocs.letmeknow.R.id.item_cm_avatar;
import static org.gocs.letmeknow.R.id.item_cm_name;

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

        initData();
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerview_ci_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new CiAdapter(ciList);
        mRecyclerView.setAdapter(mAdapter);

        return view;
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
            //holder.mCiAvatar.setImageDrawable(ci.getAvatar());
            //TODO 图像如何取回并加载？ 使用picaso？ 是否需要在Member类中加入新的属性？
            holder.mCiName.setText(ci.getUserName());
        }

        @Override
        public int getItemCount(){
            return ciList.size();
        }
    }
}
