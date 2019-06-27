package com.company.dao;

import com.company.factory.DAOFactory;
import com.company.entity.AllInform;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class AllInformDAOTest {
    private AllInformDAO allInformDAO;
    @Before
    public void setUp() throws Exception {
        allInformDAO = DAOFactory.getAllInformDAOInstance();
    }

    @Test
    public void getAllInform() throws Exception {
        List<AllInform> list = allInformDAO.getAllInform();
        list.forEach(allInform -> System.out.println(allInform));
    }

}