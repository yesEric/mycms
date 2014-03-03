package com.mycms.service.impl;

import com.mycms.dao.GenericDao;
import com.mycms.dao.ResourceDao;
import com.mycms.model.Resource;
import com.mycms.service.ResourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("resourceManager")
public class ResourceManagerImpl extends GenericManagerImpl<Resource, Long> implements ResourceManager {

    private ResourceDao resourceDao;

    @Autowired
    public ResourceManagerImpl(ResourceDao resourceDao) {
        super(resourceDao);
        this.resourceDao = resourceDao;
    }

    @Override
    public List<Resource> findTopMenus() {
        return resourceDao.findTopMenus();
    }

    @Override
    public List<Resource> findResourcesByParent(Resource parent) {
        return resourceDao.findByParent(parent);
    }
}
