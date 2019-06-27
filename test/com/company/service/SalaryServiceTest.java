package com.company.service;

import com.company.factory.ServiceFactory;
import com.company.entity.Salary;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class SalaryServiceTest {
    private SalaryService salaryService;
    @Before
    public void setUp() throws Exception {
        salaryService = ServiceFactory.getSalaryServiceInstance();
    }

    @Test
    public void getAllSalary() throws Exception {
        List<Salary> list = salaryService.getAllSalary();
        list.forEach(salary -> System.out.println(salary));
    }

}