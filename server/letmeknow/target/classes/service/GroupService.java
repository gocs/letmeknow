package service;

import model.GroupDetail;
import model.GroupMemQueryForm;
import model.GroupQueryForm;

import java.util.List;

/**
 * Created by admin on 2017/7/3.
 */
public interface GroupService {
    public List<GroupQueryForm> queryallGroups();

    public List<GroupQueryForm> queryGroupById(int groupId);

    public List<GroupQueryForm> queryGroupByName(String groupName, int start, int count);

    public GroupDetail queryGroupDetail(int groupId);

    public boolean adminExistGroup(int groupId);

    public List<GroupMemQueryForm> queryGroupMembers(int groupId);

    public GroupDetail setGroupPrivilege(int groupId, int privilege);

    public GroupDetail setGroupStatus(int groupId, int status);

    public GroupDetail deleteGroupIcon(int groupId);
    }
