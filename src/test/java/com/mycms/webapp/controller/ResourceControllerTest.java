package com.mycms.webapp.controller;

import com.mycms.model.Resource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Eric on 14-2-19.
 */
public class ResourceControllerTest extends BaseControllerTestCase {
    @Autowired
    ResourceController resourceController;
    private MockHttpServletRequest request;

    @Test
    public void testFindByParent() {
        request = newGet("/resources");
        request.addParameter("parentId", "1");
        ModelAndView mav = resourceController.listResources(request);
        ModelMap modelMap = mav.getModelMap();
        List list = (List) modelMap.get("resourceList");
        Resource resource = (Resource) list.get(0);
        resource.getSubResources();
        assertNotNull(modelMap.get("resourceList"));
        assertTrue(((List) modelMap.get("resourceList")).size() > 0);

    }
}
