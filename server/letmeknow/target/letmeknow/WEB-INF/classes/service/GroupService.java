package service;

import model.GroupQueryForm;
import model.Groups;

import java.util.List;

/**
 * Created by admin on 2017/7/3.
 */
public interface GroupService {
    public List<GroupQueryForm>queryallGroups();
    public List<GroupQueryForm>queryGroupById(int groupId);
    public List<GroupQueryForm>queryGroupByName(String groupName, int start, int count);
    public Groups queryGroupDetail(int groupId);
    public boolean adminExistGroup(int groupId);
}
