package com.company.dao;

import com.company.factory.DAOFactory;
import com.company.entity.Check;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;


public class CheckDAOTest {



    private CheckDAO checkDAO;
    @Before
    public void setUp() throws Exception {
        checkDAO = DAOFactory.getCheckDAOInstance();
    }

    @Test
    public void getAllCheck() throws Exception {
        List<Check> list = checkDAO.getAllCheck();
        list.forEach(check -> System.out.println(check));
    }

    @Test
    public void getAllCheckBySN() throws Exception {
        List<Check> list = checkDAO.getAllCheckBySN("20010");
        list.forEach(check -> System.out.println(check));
    }

    @Test
    public void deleteCheckBySN() throws Exception {
        int n ;
        n = checkDAO.deleteCheckBySN("20015");
        assertEquals(n,n);
    }

    @Test
    public void makeOneCheckStatistics() throws Exception {
        Map<String,Integer> map = checkDAO.makeOneCheckStatistics("111");
        System.out.println(map);
    }

    @Test
    public void makeAllCheckStatistics() throws Exception {

    }

}