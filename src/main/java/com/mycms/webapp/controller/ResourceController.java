package com.mycms.webapp.controller;

import com.mycms.model.Resource;
import com.mycms.service.ResourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ResourceController {
    private ResourceManager resourceManager;

    @Autowired
    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    @RequestMapping("/resources")
    public ModelAndView listResources(HttpServletRequest request) {

        //默认显示的是顶层项目
        Long parentId = null;
        if (request.getParameter("parentId") == null) {
            parentId = 1L;
        } else {
            parentId = new Long(request.getParameter("parentId"));
        }

        Resource parent = new Resource();
        parent.setId(parentId);
        ModelAndView mav = new ModelAndView().addObject("resourceList", resourceManager.findResourcesByParent(parent));
        mav.setViewName("resourceList");
        return mav;
    }
}
