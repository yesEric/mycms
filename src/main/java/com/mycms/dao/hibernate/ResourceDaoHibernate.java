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

    /**
     * 查找资源表中的菜单项目
     *
     * @return 包含顶层菜单项的资源对象.
     */
    @Override
    public List<Resource> findTopMenus() {
        return this.findByParentIdAndType(new Long(1), new Integer(1));

    }

    /**
     * 根据父辈代码和类型查找资源对象.
     *
     * @param parentId 父辈代码
     * @param type     类型:1:菜单,2:按钮,3:其他
     * @return 根据父辈代码和类型查找资源对象.
     */
    private List<Resource> findByParentIdAndType(Long parentId, Integer type) {
        Resource parent = new Resource();
        parent.setId(parentId);
        return this.getSession().createCriteria(Resource.class).add(Restrictions.eq("parent", parent)).add(Restrictions.eq("type", type)).list();
    }

    /**
     * 根据父辈代码查找子项目
     *
     * @param parentId 父辈代码
     * @return 符合条件的查询结果
     */
    @Override
    public List<Resource> findByParent(Long parentId) {
        Resource parent = new Resource();
        parent.setId(parentId);
        return this.getSession().createCriteria(Resource.class).add(Restrictions.eq("parent", parent)).list();
    }
}
