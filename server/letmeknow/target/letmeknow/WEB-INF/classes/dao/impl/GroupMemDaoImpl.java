package dao.impl;

import dao.GroupMemDao;
import model.GroupMem;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by admin on 2017/7/5.
 */
public class GroupMemDaoImpl extends CommonDaoImpl<GroupMem> implements GroupMemDao {
    public List<GroupMem> getGroupMemByGroupId(int groupId){
        return (List<GroupMem>) getHibernateTemplate().find("from GroupMem as gm where gm.groupId=?",groupId);
    }

    public GroupMem findGroupMasterByGroupId(int groupId){
        return (GroupMem)getHibernateTemplate().find("from GroupMem as gm where gm.groupId=? and role='master'",groupId).get(0);
    }
}
