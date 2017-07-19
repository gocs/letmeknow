package org.gocs.letmeknow.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.StateSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bignerdranch.android.multiselector.ModalMultiSelectorCallback;
import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SwappingHolder;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.application.Constants;
import org.gocs.letmeknow.model.Member;
import org.gocs.letmeknow.network.RetrofitClient;
import org.gocs.letmeknow.util.ToastUtils;
import org.gocs.letmeknow.util.handler.NetworkErrorHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dynamicheart on 7/18/2017.
 */

public class SelectMemberActivity extends BaseActivity{

    public static final String GROUP_ID = "GROUP_ID";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview_select_member_list)
    RecyclerView recyclerViewMemberList;

    private MultiSelector multiSelector = new MultiSelector();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        setupRecyclerView();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_select_members;
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //set toolbar title
        getSupportActionBar().setTitle(R.string.select_members);
    }


    @SuppressWarnings("unchecked")
    private void setupRecyclerView(){
        Intent intent = getIntent();
        String groupId = intent.getStringExtra(GROUP_ID);
        RetrofitClient.getService()
                .getCircleMembers(groupId)
                .flatMap(NetworkErrorHandler.ErrorFilter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response->{
                    recyclerViewMemberList.setLayoutManager(new LinearLayoutManager(recyclerViewMemberList.getContext()));
                    recyclerViewMemberList.setAdapter(new SelectMemberRecyclerViewAdapter((List<Member>) response.getData().get(Constants.JSON_KEY_MEMBERS), this));

                }, NetworkErrorHandler.basicErrorHandler);
    }


    private class SelectMemberRecyclerViewAdapter extends RecyclerView.Adapter<SelectMemberRecyclerViewAdapter.ViewHolder>{
        private List<Member> mMemberList = new ArrayList<>();
        private Context mContext;

        class ViewHolder extends SwappingHolder implements CompoundButton.OnCheckedChangeListener {

            private final TextView mTextViewMemberName;
            private final CheckBox mCheckBox;

            private ViewHolder(View view) {
                super(view, multiSelector);

                mTextViewMemberName = (TextView) view.findViewById(R.id.item_selectable_member_text_member_name);
                mCheckBox = (CheckBox)view.findViewById(R.id.item_selectable_member_checkbox);
                mCheckBox.setOnCheckedChangeListener(this);
            }


            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!multiSelector.isSelectable()) {
                    multiSelector.setSelectable(true);
                }
                multiSelector.setSelected(this, isChecked);
            }
        }

        SelectMemberRecyclerViewAdapter(List<Member> memberList, Context context) {
            this.mMemberList = memberList;
            this.mContext = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_selectable_member, parent, false);
            return new SelectMemberRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mTextViewMemberName.setText(mMemberList.get(position).getUserName());
            holder.setSelectionModeBackgroundDrawable(holder.getDefaultModeBackgroundDrawable());
            holder.setSelectionModeStateListAnimator(holder.getDefaultModeStateListAnimator());
        }

        @Override
        public int getItemCount() {
            return mMemberList.size();
        }
    }


}
