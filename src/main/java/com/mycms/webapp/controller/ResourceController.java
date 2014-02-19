package com.mycms.webapp.controller;

import com.mycms.model.Resource;
import com.mycms.service.ResourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResourceController {
    private ResourceManager resourceManager;

    @Autowired
    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    @RequestMapping("/topMenus")
    public ModelAndView findTopMenus() {
        ModelAndView mav = new ModelAndView().addObject("topMenuList", resourceManager.findTopMenus());

        mav.setViewName("index");
        return mav;
    }
}
