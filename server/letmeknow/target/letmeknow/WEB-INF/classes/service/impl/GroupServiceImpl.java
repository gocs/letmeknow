package service.impl;

import dao.GroupDao;
import model.GroupQueryForm;
import model.Groups;
import service.GroupService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/7/3.
 */
public class GroupServiceImpl implements GroupService {
    private GroupDao groupDao;

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

    public Groups queryGroupDetail(int groupId) {
        return groupDao.getGroupsById(groupId);
    }

    public boolean adminExistGroup(int groupId) {
        if(groupDao.getGroupsById(groupId)!=null) return true;
        return false;
    }


}
