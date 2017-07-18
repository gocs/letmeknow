package dao;

import model.GroupMem;
import model.GroupMemQueryForm;
import model.GroupMemWithAvatar;

import java.util.List;

/**
 * Created by admin on 2017/7/5.
 */
public interface GroupMemDao extends CommonDao<GroupMem> {
    public List<GroupMem> getGroupMemByGroupId(int groupId);

    public GroupMem findGroupMasterByGroupId(int groupId);

    public List<GroupMemWithAvatar> getNotifiersByGroupId(int groupId);

    public List<GroupMemWithAvatar> CommonGetMembersByGroupId(int groupId);

    //public void deleteMembersByUserIdList(final List<Integer> userId, final int groupId);

    public GroupMem getMemberByUserGroupId(int userId, int groupId);
    }
