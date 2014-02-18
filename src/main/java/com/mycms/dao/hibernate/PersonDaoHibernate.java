package com.mycms.dao.hibernate;

import com.mycms.dao.PersonDao;
import com.mycms.model.Person;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("personDao")
public class PersonDaoHibernate extends GenericDaoHibernate<Person, Long> implements PersonDao {


    public PersonDaoHibernate() {
        super(Person.class);
    }


    public List<Person> findByLastName(String lastName) {
        return this.getSession().createCriteria(Person.class).add(Restrictions.eq("lastName", lastName)).list();
    }
}
