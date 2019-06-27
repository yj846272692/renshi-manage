package com.company.dao;

import com.company.factory.DAOFactory;
import com.company.entity.PersonalInform;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;


public class PersonalInformDAOTest {



    private PersonalInformDAO personalInformDAO;
    @Before
    public void setUp() throws Exception {
        personalInformDAO = DAOFactory.getPersonalInformDAOInstance();
    }

    @Test
    public void getAllInform() throws Exception {
        List<PersonalInform> list = personalInformDAO.getAllInform();
        list.forEach(personalInform -> System.out.println(personalInform));
    }

    @Test
    public void getAllInformBySN() throws Exception {
        List<PersonalInform> list = personalInformDAO.getAllInformBySN("20010");
        list.forEach(personalInform -> System.out.println(personalInform));
    }

    @Test
    public void deleteInforms() throws Exception {
        int n;
        n = personalInformDAO.deleteInforms("20013");
        assertEquals(n,n);
    }

}