package com.mycms.dao;

import com.mycms.model.Person;

import java.util.List;

/**
 * Created by Eric on 14-2-8.
 */
public interface PersonDao extends GenericDao<Person, Long> {

    List<Person> findByLastName(String lastName);
}
