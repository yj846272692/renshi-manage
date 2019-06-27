package com.company.dao;

import com.company.factory.DAOFactory;
import com.company.entity.LimitMap;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LimitMapDAOTest {


    private LimitMapDAO limitMapDAO;
    @Before
    public void setUp() throws Exception {
        limitMapDAO = DAOFactory.getLimitMapDAOInstance();
    }

    @Test
    public void getLimitMap() throws Exception {
        LimitMap limitMap = limitMapDAO.getLimitMap("20010");
        System.out.println(limitMap);
    }

    @Test
    public void addOneLimitMap() throws Exception {
        int n;
        LimitMap map = new LimitMap("11111","2222");
        n = limitMapDAO.addOneLimitMap(map);
        assertEquals(1,n);
    }



    @Test
    public void deleteOneLimitMap() throws Exception {
        int n;
        n = limitMapDAO.deleteOneLimitMap("11111","2222");
        assertEquals(1,n);
    }

}