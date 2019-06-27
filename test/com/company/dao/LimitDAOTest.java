package com.company.dao;

import com.company.factory.DAOFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;


public class LimitDAOTest {


    private LimitDAO limitDAO;
    @Before
    public void setUp() throws Exception {
       limitDAO = DAOFactory.getLimitDAOInstance();
    }

    @Test
    public void getGroupName() throws Exception {
        List<String> strings = limitDAO.getGroupName("20010");
        strings.forEach(s -> System.out.println(s));
    }

    @Test
    public void getItemName() throws Exception {
        List<String> strings = limitDAO.getGroupName("20010");
        Map<String,List<String>> map = limitDAO.getItemName(strings,"20010");
        System.out.println(map);
    }

    @Test
    public void getItemIDAndName() throws Exception {
        Map<String,String> map = limitDAO.getItemIDAndName("20010");
        System.out.println(map);
    }

}