package com.company.service;

import com.company.factory.ServiceFactory;
import com.company.entity.Department;
import org.junit.Before;
import org.junit.Test;


public class DepartmentServiceTest {
    private DepartmentService departmentService;
    @Before
    public void setUp() throws Exception {
        departmentService = ServiceFactory.getDepartmentServiceInstance();
    }

    @Test
    public void getOneDept() throws Exception {
        Department department = departmentService.getOneDept("1");
        System.out.println(department);
    }

}