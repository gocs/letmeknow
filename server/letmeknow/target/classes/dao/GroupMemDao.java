package dao;

import model.GroupMem;

import java.util.List;

/**
 * Created by admin on 2017/7/5.
 */
public interface GroupMemDao extends CommonDao<GroupMem> {
    public List<GroupMem> getGroupMemByGroupId(int groupId);

    public GroupMem findGroupMasterByGroupId(int groupId);

}
