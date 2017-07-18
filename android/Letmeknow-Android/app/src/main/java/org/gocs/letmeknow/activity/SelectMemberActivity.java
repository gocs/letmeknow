package org.gocs.letmeknow.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SwappingHolder;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.application.Constants;
import org.gocs.letmeknow.model.CircleBrief;
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

    public static final String GROUP_BRIEF = "GROUP_BRIEF";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview_select_member_list)
    RecyclerView recyclerViewMemberList;

    private MultiSelector multiSelector = new MultiSelector();

    private MenuItem menuItemConfirm;

    private List<Member> allMembers;


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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_select_members, menu);
        menuItemConfirm = menu.getItem(0);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_confirm_select:
                if(multiSelector.getSelectedPositions().size() == 0){
                    return true;
                }
                Intent intent = new Intent(SelectMemberActivity.this, NotificationEditActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_SINGLE_TOP);

                ArrayList<Member> selectedMembers = new ArrayList<>();
                for(int position : multiSelector.getSelectedPositions()){
                    selectedMembers.add(allMembers.get(position));
                }
                ToastUtils.showShortToast(selectedMembers.toString());
                intent.putExtra(NotificationEditActivity.MEMBER_LIST_SERIALIZABLE, selectedMembers);
                intent.putExtra(GROUP_BRIEF, getIntent().getSerializableExtra(GROUP_BRIEF));
                startActivity(intent);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("unchecked")
    private void setupRecyclerView(){
        Intent intent = getIntent();
        String groupId = ((CircleBrief)intent.getSerializableExtra(GROUP_BRIEF)).getGroupId();
        RetrofitClient.getService()
                .getCircleMembers(groupId)
                .flatMap(NetworkErrorHandler.ErrorFilter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response->{
                    allMembers = (List<Member>) response.getData().get(Constants.JSON_KEY_MEMBERS);

                    recyclerViewMemberList.setLayoutManager(new LinearLayoutManager(recyclerViewMemberList.getContext()));
                    recyclerViewMemberList.setAdapter(new SelectMemberRecyclerViewAdapter(allMembers, this));

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
                if(multiSelector.getSelectedPositions().size() == 0){
                    menuItemConfirm.setTitle(R.string.confirm_select);
                }else {
                    String title = getString(R.string.confirm_select) + "(" + multiSelector.getSelectedPositions().size() + ")";
                    menuItemConfirm.setTitle(title);
                }
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

        public List<Member> getList(){
            return mMemberList;
        }
    }


}
