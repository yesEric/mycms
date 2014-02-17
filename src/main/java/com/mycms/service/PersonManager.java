package com.mycms.service;

import com.mycms.model.Person;

import java.util.List;

/**
 * Created by Eric on 14-2-8.
 */
public interface PersonManager extends GenericManager<Person, Long> {
    List<Person> findByLastName(String lastName);
}
