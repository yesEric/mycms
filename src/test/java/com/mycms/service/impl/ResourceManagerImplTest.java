package com.mycms.service.impl;

import com.mycms.dao.ResourceDao;
import com.mycms.model.Resource;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.junit.Assert.*;

/**
 * Created by Eric on 14-2-19.
 */
public class ResourceManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private ResourceManagerImpl manager;

    @Mock
    private ResourceDao dao;

    @Test
    public void testGetResource() {
        log.debug("test get resource");
        //given
        final Long id = 7L;
        final Resource resource = new Resource();
        given(dao.get(id)).willReturn(resource);
        //when
        Resource result = manager.get(id);
        //then
        assertSame(resource, result);

    }

    @Test
    public void testFindTopMenus() {
        log.debug("test findTopMenus");
        //given
        final List<Resource> resourceList = new ArrayList<Resource>();
        given(dao.findTopMenus()).willReturn(resourceList);

        //when

        List<Resource> results = manager.findTopMenus();
        //then

        assertSame(resourceList, results);
    }

}
