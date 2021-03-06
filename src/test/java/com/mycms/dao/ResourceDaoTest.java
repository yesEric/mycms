package com.mycms.dao;

import com.mycms.model.Resource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;

import java.util.List;

public class ResourceDaoTest extends BaseDaoTestCase {

    @Autowired
    private ResourceDao resourceDao;

    @Test
    public void testFindResourceByName() throws Exception {
        List<Resource> resourceList = resourceDao.findByName("产品与目录管理");
        assertTrue(resourceList.size() > 0);
        Resource productMenu = resourceList.get(0);
        assertTrue(productMenu.getSubResources().size() > 0);
    }


    @Test(expected = DataAccessException.class)
    public void testAddAndRemoveResourceDao() throws Exception {
        Resource resource = new Resource();

        resource.setName("Eric");

        resource = resourceDao.save(resource);

        assertEquals("Eric", resource.getName());
        assertNotNull(resource.getId());

        log.debug("removing ResourceDao....");
        resourceDao.remove(resource.getId());
        flush();
        //这里应该会抛出异常
        resourceDao.get(resource.getId());

    }

    @Test
    public void testFindTopMenus() {
        List<Resource> topMenus = resourceDao.findTopMenus();

        assertNotNull(topMenus);
        assertTrue(topMenus.size() > 0);


    }

    @Test
    public void testFindByParent() {
        List<Resource> resourceList = resourceDao.findByParent(1L);

        assertNotNull(resourceList);
        assertTrue(resourceList.size() > 0);


    }
}