package com.company.dao;

import com.company.factory.DAOFactory;
import com.company.entity.CheckStatistics;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class CheckStatisticsDAOTest {


    private CheckStatisticsDAO dao;
    @Before
    public void setUp() throws Exception {
        dao = DAOFactory.getCheckStatisticsDAOInstance();
    }

    @Test
    public void addOneCheckSta() throws Exception {

    }

    @Test
    public void deleteCheckSta() throws Exception {
        int n;
        n = dao.deleteCheckSta("20015");
        assertEquals(n,n);
    }

    @Test
    public void getAllCheckSta() throws Exception {
        List<CheckStatistics> list = dao.getAllCheckSta();
        list.forEach(checkStatistics -> System.out.println(checkStatistics));
    }

}