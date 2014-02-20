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
 * Created by Eric on 14-2-20.
 */
public class HomeControllerTest extends BaseControllerTestCase {
    @Autowired
    HomeController homeController;

    @Test
    public void testFindTopMenus() {
        ModelAndView mav = homeController.home();
        ModelMap modelMap = mav.getModelMap();
        List list = (List) modelMap.get("menus");
        Resource resource = (Resource) list.get(0);
        resource.getSubResources().isEmpty();
        assertNotNull(modelMap.get("menus"));
        assertTrue(((List) modelMap.get("menus")).size() > 0);

    }
}
