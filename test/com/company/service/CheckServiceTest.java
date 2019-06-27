package com.company.service;

import com.company.factory.ServiceFactory;
import com.company.entity.Check;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;


public class CheckServiceTest {



    private CheckService checkService;
    @Before
    public void setUp() throws Exception {
        checkService = ServiceFactory.getCheckServiceInstance();
    }

    @Test
    public void getAllCheck() throws Exception {
        List<Check> list = checkService.getAllCheck();
        list.forEach(check -> System.out.println(check));

    }

    @Test
    public void getAllCheckBySN() throws Exception {
        List<Check> list = checkService.getAllCheckBySN("20010");
        list.forEach(check -> System.out.println(check));
    }

    @Test
    public void makeOneCheckStatistics() throws Exception {
        Map<String,Integer> map = checkService.makeOneCheckStatistics("111");
        System.out.println(map);
    }

    @Test
    public void makeAllCheckStatistics() throws Exception {

    }

}