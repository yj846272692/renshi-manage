package com.company.dao;

import com.company.factory.DAOFactory;
import com.company.entity.LimitItem;
import org.junit.Before;
import org.junit.Test;


public class LimitItemDAOTest {


    private LimitItemDAO limitItemDAO;
    @Before
    public void setUp() throws Exception {
        limitItemDAO = DAOFactory.getLimitItemDAOInstance();
    }

    @Test
    public void getLimitItem() throws Exception {
        LimitItem limitItem = limitItemDAO.getLimitItem("1");
        System.out.println(limitItem);
    }

    @Test
    public void getLimitItemID() throws Exception {
        String itemID = limitItemDAO.getLimitItemID("修改权限");
        System.out.println(itemID);
    }

}