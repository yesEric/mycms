package com.mycms.webapp.controller;

import com.mycms.model.Resource;
import com.mycms.service.ResourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Eric on 14-2-19.
 */
@Controller
@SessionAttributes({"menus"})
/**
 * 登录主页的控制器.
 */
public class HomeController {
    private ResourceManager resourceManager;

    @Autowired
    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    @RequestMapping("/home")
    public ModelAndView home() {

        ModelAndView modelAndView = new ModelAndView();
        List<Resource> menus = resourceManager.findTopMenus();
        modelAndView.addObject("menus", menus);
        modelAndView.setViewName("index");

        return modelAndView;
    }
}
