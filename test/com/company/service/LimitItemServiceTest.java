package com.company.service;

import com.company.factory.ServiceFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class LimitItemServiceTest {
    private LimitItemService service ;
    @Before
    public void setUp() throws Exception {
        service = ServiceFactory.getLimitItemServiceInstance();
    }

    @Test
    public void getLimitItemID() throws Exception {
        String itemID = service.getLimitItemID("核算工资");
        System.out.println(itemID);
    }

}