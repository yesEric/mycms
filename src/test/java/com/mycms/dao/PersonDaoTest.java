package com.mycms.dao;

import com.mycms.model.BaseObject;
import com.mycms.model.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Eric on 14-2-8.
 */
public class PersonDaoTest extends BaseDaoTestCase {

    @Autowired
    private PersonDao personDao;

    @Test
    public void testFindPersonByLastName() throws Exception {
        List<Person> personList = personDao.findByLastName("Liu");
        assertTrue(personList.size() > 0);
    }

    @Test(expected = DataAccessException.class)
    public void testAddAndRemovePerson() throws Exception {
        Person person = new Person();
        person.setFirstName("Henry");
        person.setLastName("Ma");

        person = personDao.save(person);

        assertEquals("Henry", person.getFirstName());
        assertNotNull(person.getId());

        log.debug("removing person....");
        personDao.remove(person.getId());
        flush();
        //这里应该会抛出异常
        personDao.get(person.getId());

    }
}
