package com.company.dao;

import com.company.factory.DAOFactory;
import com.company.entity.LimitGroup;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class LimitGroupDAOTest {


    private LimitGroupDAO limitGroupDAO;
    @Before
    public void setUp() throws Exception {
        limitGroupDAO = DAOFactory.getLimitGroupDAOInstance();
    }

    @Test
    public void getLimitGroup() throws Exception {
        LimitGroup limitGroup = limitGroupDAO.getLimitGroup("1");
        System.out.println(limitGroup);
    }

    @Test
    public void getAllLimitGroup() throws Exception {
        List<LimitGroup> list = limitGroupDAO.getAllLimitGroup();
        list.forEach(limitGroup -> System.out.println(limitGroup));
    }

}