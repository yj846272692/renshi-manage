package com.company.service;

import com.company.factory.ServiceFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class LimitServiceTest {
    private LimitService service ;
    @Before
    public void setUp() throws Exception {
        service = ServiceFactory.getLimitServiceInstance();
    }

    @Test
    public void getGroupName() throws Exception {
        List<String> list = service.getGroupName("20010");
        list.forEach(s -> System.out.println(s));
    }

    @Test
    public void getItemName() throws Exception {
        List<String> list = service.getGroupName("20010");
        Map<String,List<String>> listMap = service.getItemName(list,"20010");
        System.out.println(listMap);
    }

}