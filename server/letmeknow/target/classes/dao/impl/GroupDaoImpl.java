package dao.impl;

import dao.GroupDao;
import model.GroupWithRole;
import model.Groups;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/7/3.
 */
public class GroupDaoImpl extends HibernateDaoSupport implements GroupDao {
    public Integer save(Groups group) {
        return (Integer) getHibernateTemplate().save(group);
    }

    public void delete(Groups group) {
        getHibernateTemplate().delete(group);
    }

    public void update(Groups group) {
        getHibernateTemplate().update(group);
    }

    public Groups getGroupsById(int groupId) {
        List<Groups> groups = (List<Groups>) getHibernateTemplate().find("from Groups as g where g.groupId=?", groupId);
        return groups.size() > 0 ? groups.get(0) : null;
    }

    public List<Groups> getAllGroupss() {
        return (List<Groups>) getHibernateTemplate().find("from Groups ");
    }

    public Groups getGroupsByName(String groupName) {
        List<Groups> groups = (List<Groups>) getHibernateTemplate().find("from Groups as g where g.groupName=?", groupName);
        return groups.size() > 0 ? groups.get(0) : null;
    }

    public List<Groups> getGroupsByName(final String groupName, final int start, final int count) {
        return (List<Groups>) getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return session.createQuery("from Groups where groupName like '%" + groupName + "%'").setFirstResult(start).setMaxResults(count).list();
            }
        });
    }

    public List<Groups> getGroupsByIdList(final List<Integer> groupId) {
        return getHibernateTemplate().execute(new HibernateCallback<List<Groups>>() {
            @SuppressWarnings("unchecked")
            public List<Groups> doInHibernate(Session session)
                    throws HibernateException, SQLException {
                return groupId.size() == 0 ? new ArrayList<Groups>() : session.createQuery("from Groups g where g.groupId in(:groupId) and g.status=2")
                        .setParameterList("groupId", groupId).list();
            }
        });
    }

    public List<GroupWithRole> getGroupsByUserId( int userId) {
        List<GroupWithRole> res=GroupWithRole.convertToForm((List<Object[]>)getHibernateTemplate().find("from Groups as g, GroupMem as gm where g.groupId=gm.groupId and gm.userId=? and gm.deletedAt is null and g.status=2",userId));
        return res;
    }
}
