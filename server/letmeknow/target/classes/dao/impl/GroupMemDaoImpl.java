package dao.impl;

import dao.GroupMemDao;
import model.GroupMem;
import model.GroupMemQueryForm;
import model.GroupMemWithAvatar;
import model.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.ArrayList;
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

    public List<GroupMemWithAvatar> getNotifiersByGroupId(int groupId){
        List<Object[]> notifiers=(List<Object[]>)getHibernateTemplate().find("from GroupMem as gm,User as u where u.userId=gm.userId and gm.groupId=? and (gm.role='notifier' or gm.role='master') and u.status=2 and gm.deletedAt is null",groupId);
        List<GroupMemWithAvatar> res=new ArrayList<GroupMemWithAvatar>();
        for(Object[] composition:notifiers)
            res.add(new GroupMemWithAvatar((GroupMem)composition[0],(User)composition[1]));
        return res;
    }

    public List<GroupMemWithAvatar> CommonGetMembersByGroupId(int groupId){
        List<Object[]> notifiers=(List<Object[]>)getHibernateTemplate().find("from GroupMem as gm,User as u where u.userId=gm.userId and gm.groupId=? and u.status!=2 and gm.deletedAt is null",groupId);
        List<GroupMemWithAvatar> res=new ArrayList<GroupMemWithAvatar>();
        for(Object[] composition:notifiers)
            res.add(new GroupMemWithAvatar((GroupMem)composition[0],(User)composition[1]));
        return res;
    }

}
