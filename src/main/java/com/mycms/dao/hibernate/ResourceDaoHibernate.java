package com.mycms.dao.hibernate;

import com.mycms.dao.ResourceDao;
import com.mycms.model.Resource;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Eric on 14-2-18.
 */
@Repository("resourceDao")
public class ResourceDaoHibernate extends GenericDaoHibernate<Resource, Long> implements ResourceDao {
    public ResourceDaoHibernate() {
        super(Resource.class);
    }

    @Override
    public List<Resource> findByName(String name) {
        return this.getSession().createCriteria(Resource.class).add(Restrictions.eq("name", name)).list();
    }
}
