package com.mycms.webapp.controller;

import com.mycms.model.Resource;
import com.mycms.service.ResourceManager;
import org.apache.commons.lang.StringUtils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/resourceform*")
public class ResourceFormController extends BaseFormController {
    private ResourceManager resourceManager = null;

    @Autowired
    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public ResourceFormController() {
        setCancelView("redirect:resources");
        setSuccessView("redirect:resources");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Resource showForm(HttpServletRequest request)
            throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return resourceManager.get(new Long(id));
        }

        return new Resource();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Resource resource, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(resource, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "resourceform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (resource.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            resourceManager.remove(resource.getId());
            saveMessage(request, getText("resource.deleted", locale));
        } else {
            resourceManager.save(resource);
            String key = (isNew) ? "resource.added" : "resource.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:resourceform?id=" + resource.getId();
            }
        }

        return success;
    }
}