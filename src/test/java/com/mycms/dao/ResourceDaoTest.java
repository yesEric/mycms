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
        List<Resource> resourceList = resourceDao.findByName("Resource");
        assertTrue(resourceList.size() > 0);
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
}