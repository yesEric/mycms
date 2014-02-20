package com.mycms.webapp.controller;

import com.mycms.model.Resource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Test
    public void testFindTopMenus() {
        ModelAndView mav = resourceController.findTopMenus();
        ModelMap modelMap = mav.getModelMap();
        List list = (List) modelMap.get("topMenuList");
        Resource resource = (Resource) list.get(0);
        resource.getSubResources();
        assertNotNull(modelMap.get("topMenuList"));
        assertTrue(((List) modelMap.get("topMenuList")).size() > 0);

    }
}
