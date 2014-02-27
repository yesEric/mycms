package com.mycms.service;

import com.mycms.model.Resource;

import java.util.List;

/**
 * Created by Eric on 14-2-19.
 */
public interface ResourceManager extends GenericManager<Resource, Long> {
    List<Resource> findTopMenus();

    List<Resource> findResourcesByParent(Long parentId);
}
