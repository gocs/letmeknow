package dao;

import model.GroupsEntity;

import java.util.List;

/**
 * Created by admin on 2017/7/3.
 */
public interface GroupDao {
    public Integer save(GroupsEntity group);

    public void delete(GroupsEntity group);

    public void update(GroupsEntity group);

    public GroupsEntity getGroupsEntityById(int groupId);

    public List<GroupsEntity> getAllGroupsEntitys();

    public GroupsEntity getGroupsEntityByName(String groupName);

    public List<GroupsEntity> getGroupsEntityByName(String groupName, int start, int count);
}
