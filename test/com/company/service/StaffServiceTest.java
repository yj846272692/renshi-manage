package com.company.service;

import com.company.factory.ServiceFactory;
import com.company.entity.Staff;
import org.junit.Before;
import org.junit.Test;


public class StaffServiceTest {
    private StaffService staffService;
    @Before
    public void setUp() throws Exception {
        staffService = ServiceFactory.getStaffServiceInstance();
    }

    @Test
    public void getOndStaff() throws Exception {
        Staff staff = staffService.getOndStaff("20010");
        System.out.println(staff);
    }

}