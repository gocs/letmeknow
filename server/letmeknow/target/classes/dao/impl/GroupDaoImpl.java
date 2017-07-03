package dao.impl;

import dao.GroupDao;
import model.GroupsEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 2017/7/3.
 */
public class GroupDaoImpl extends HibernateDaoSupport implements GroupDao {
    public Integer save(GroupsEntity group) {
        return (Integer) getHibernateTemplate().save(group);
    }

    public void delete(GroupsEntity group) {
        getHibernateTemplate().delete(group);
    }

    public void update(GroupsEntity group) {
        getHibernateTemplate().update(group);
    }

    public GroupsEntity getGroupsEntityById(int groupId) {
        List<GroupsEntity> groups = (List<GroupsEntity>) getHibernateTemplate().find("from GroupsEntity as g where g.groupId=?", groupId);
        return groups.size() > 0 ? groups.get(0) : null;
    }

    public List<GroupsEntity> getAllGroupsEntitys() {
        return (List<GroupsEntity>) getHibernateTemplate().find("from GroupsEntity ");
    }

    public GroupsEntity getGroupsEntityByName(String groupName) {
        List<GroupsEntity> groups = (List<GroupsEntity>) getHibernateTemplate().find("from GroupsEntity as g where g.groupName=?", groupName);
        return groups.size() > 0 ? groups.get(0) : null;
    }

    public List<GroupsEntity> getGroupsEntityByName(final String groupName, final int start, final int count) {
        return (List<GroupsEntity>) getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return session.createQuery("from GroupsEntity where groupName like '%"+groupName+"%'").setFirstResult(start).setMaxResults(count).list();
            }
        });
    }
}
