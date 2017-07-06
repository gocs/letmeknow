package service.impl;

import dao.GroupDao;
import dao.GroupMemDao;
import dao.UserDao;
import model.*;
import org.apache.struts2.ServletActionContext;
import service.GroupService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/7/3.
 */
public class GroupServiceImpl implements GroupService {
    private GroupDao groupDao;
    private GroupMemDao groupMemDao;
    private UserDao userDao;

    public void setUserDao(UserDao userDao){this.userDao=userDao;}

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
        List<GroupMem> groupMems=groupMemDao.getGroupMemByGroupId(groupId);
        return new GroupDetail(groupDao.getGroupsById(groupId),masterName,groupMems.size());
    }

    public boolean adminExistGroup(int groupId) {
        if (groupDao.getGroupsById(groupId) != null) return true;
        return false;
    }

    public List<GroupMemQueryForm> queryGroupMembers(int groupId){
        List<GroupMem> groupMems=groupMemDao.getGroupMemByGroupId(groupId);
        List<User> users=userDao.getAllUsers();
        List<User> targets=new ArrayList<User>();
        for(GroupMem gm:groupMems){
            for(User user:users){
                if(user.getUserId()==gm.getUserId()) {
                    targets.add(user);
                    break;
                }
            }
        }
        List<GroupMemQueryForm> res=new ArrayList<GroupMemQueryForm>();
        int i;
        for(i=0;i<targets.size();++i)
            res.add(new GroupMemQueryForm(groupMems.get(i),targets.get(i)));
        return res;
    }

    public void setGroupMemDao(GroupMemDao groupMemDao) {
        this.groupMemDao = groupMemDao;
    }
}
