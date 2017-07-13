package service.impl;

import dao.GroupDao;
import dao.GroupMemDao;
import dao.UserDao;
import model.*;
import service.GroupService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/7/3.
 */
public class GroupServiceImpl implements GroupService {
    private GroupDao groupDao;
    private GroupMemDao groupMemDao;
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public List<GroupQueryForm> queryallGroups() {
        return GroupQueryForm.convertToForm(groupDao.getAllGroupss());
    }

    public List<GroupQueryForm> queryGroupById(int groupId) {
        List<GroupQueryForm> res = new ArrayList<GroupQueryForm>();
        res.add(new GroupQueryForm(groupDao.getGroupsById(groupId)));
        return res;
    }

    public List<GroupQueryForm> queryGroupByName(String groupName, int start, int count) {
        return GroupQueryForm.convertToForm(groupDao.getGroupsByName(groupName, start, count));
    }

    public GroupDetail queryGroupDetail(int groupId) {
        GroupMem master = groupMemDao.findGroupMasterByGroupId(groupId);
        String masterName = userDao.getUserById(master.getUserId()).getUsername();
        List<GroupMem> groupMems = groupMemDao.getGroupMemByGroupId(groupId);
        return new GroupDetail(groupDao.getGroupsById(groupId), masterName, groupMems.size());
    }

    public boolean adminExistGroup(int groupId) {
        if (groupDao.getGroupsById(groupId) != null) return true;
        return false;
    }

    public List<GroupMemQueryForm> queryGroupMembers(int groupId) {
        List<GroupMem> groupMems = groupMemDao.getGroupMemByGroupId(groupId);
        List<User> users = userDao.getAllUsers();
        List<User> targets = new ArrayList<User>();
        for (GroupMem gm : groupMems) {
            for (User user : users) {
                if (user.getUserId() == gm.getUserId()) {
                    targets.add(user);
                    break;
                }
            }
        }
        List<GroupMemQueryForm> res = new ArrayList<GroupMemQueryForm>();
        int i;
        for (i = 0; i < targets.size(); ++i)
            res.add(new GroupMemQueryForm(groupMems.get(i), targets.get(i)));
        return res;
    }

    public GroupDetail setGroupPrivilege(int groupId, int privilege) {
        Groups group = groupDao.getGroupsById(groupId);
        group.setCategory(privilege);
        groupDao.update(group);
        return constructDetail(group);
    }

    public GroupDetail constructDetail(Groups group) {
        GroupMem master = groupMemDao.findGroupMasterByGroupId(group.getGroupId());
        String masterName = userDao.getUserById(master.getUserId()).getUsername();
        List<GroupMem> groupMems = groupMemDao.getGroupMemByGroupId(group.getGroupId());
        return new GroupDetail(group, masterName, groupMems.size());
    }

    public GroupDetail setGroupStatus(int groupId, int status) {
        Groups group = groupDao.getGroupsById(groupId);
        if (status == 2 && group.getStatus() == 0) group.setDeletedAt(null);
        if (status == 0) group.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        group.setStatus(status);
        groupDao.update(group);
        return constructDetail(group);
    }

    public GroupDetail deleteGroupIcon(int groupId) {
        Groups group = groupDao.getGroupsById(groupId);
        group.setIcon(null);
        groupDao.update(group);
        return constructDetail(group);
    }

    public void setGroupMemDao(GroupMemDao groupMemDao) {
        this.groupMemDao = groupMemDao;
    }

    public List<GroupWithRole> CommonGetGroups(int userId){
        List<GroupWithRole> groups=groupDao.getGroupsByUserId(userId);
        return groups;
    }

    public List<GroupMemWithAvatar> queryGroupNotifiers(int groupId){
        return groupMemDao.getNotifiersByGroupId(groupId);
    }

    public List<GroupMemWithAvatar> queryCommonGroupMembers(int groupId){
        return groupMemDao.CommonGetMembersByGroupId(groupId);
    }

    public List<User> queryCommonUserListByGroupId(int groupId){
        return userDao.getCommonUserListByGroupId(groupId);
    }

    public User queryCommonUserByUserId(int userId){
        return userDao.getCommonUserById(userId);
    }

    public void updateGroup(int groupId, String introduction, String avatar, String groupName){
        Groups group=groupDao.getGroupsById(groupId);
        if(introduction!=null&&!(introduction.equals(""))) group.setIntroduction(introduction);
        if(avatar!=null&&!(avatar.equals(""))) group.setIcon(avatar);
        if(groupName!=null&&!(groupName.equals(""))) group.setGroupName(groupName);
        groupDao.update(group);
    }
}
