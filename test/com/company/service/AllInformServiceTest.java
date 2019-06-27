package com.company.service;

import com.company.factory.ServiceFactory;
import com.company.entity.AllInform;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class AllInformServiceTest {
    private AllInformService allInformService;
    @Before
    public void setUp() throws Exception {
        allInformService = ServiceFactory.getAllInformServiceInstance();
    }

    @Test
    public void getAllInform() throws Exception {
        List<AllInform> list = allInformService.getAllInform();
        list.forEach(allInform -> System.out.println(allInform));
    }

}