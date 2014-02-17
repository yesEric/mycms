package com.mycms.webapp.controller;

import com.mycms.model.Person;
import com.mycms.service.PersonManager;
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
@RequestMapping("/personform*")
public class PersonFormController extends BaseFormController {
    private PersonManager personManager = null;

    @Autowired
    public void setPersonManager(PersonManager personManager) {
        this.personManager = personManager;
    }

    public PersonFormController() {
        setCancelView("redirect:persons");
        setSuccessView("redirect:persons");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Person showForm(HttpServletRequest request)
            throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return personManager.get(new Long(id));
        }

        return new Person();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Person person, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(person, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "personform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (person.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            personManager.remove(person.getId());
            saveMessage(request, getText("person.deleted", locale));
        } else {
            personManager.save(person);
            String key = (isNew) ? "person.added" : "person.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:personform?id=" + person.getId();
            }
        }

        return success;
    }
}