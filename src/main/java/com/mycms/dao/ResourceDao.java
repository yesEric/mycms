package com.mycms.dao;

import com.mycms.model.Resource;

import java.util.List;


public interface ResourceDao extends GenericDao<Resource, Long> {


    List<Resource> findByName(String name);

    List<Resource> findTopMenus();

    List findByParent(Resource parent);


}