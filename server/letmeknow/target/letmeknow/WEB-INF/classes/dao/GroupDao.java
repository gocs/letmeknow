package dao;

import model.GroupWithRole;
import model.Groups;

import java.util.List;

/**
 * Created by admin on 2017/7/3.
 */
public interface GroupDao {
    public Integer save(Groups group);

    public void delete(Groups group);

    public void update(Groups group);

    public Groups getGroupsById(int groupId);

    public List<Groups> getAllGroupss();

    public Groups getGroupsByName(String groupName);

    public List<Groups> getGroupsByName(String groupName, int start, int count);

    public List<Groups> getGroupsByIdList(final List<Integer> groupId);

    public List<GroupWithRole> getGroupsByUserId(int userId);

}
