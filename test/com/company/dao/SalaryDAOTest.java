package com.company.dao;

import com.company.factory.DAOFactory;
import com.company.entity.Salary;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class SalaryDAOTest {



    private SalaryDAO salaryDAO;
    @Before
    public void setUp() throws Exception {
        salaryDAO = DAOFactory.getSalaryDAOInstance();
    }

    @Test
    public void getAllSalary() throws Exception {
        List<Salary> list = salaryDAO.getAllSalary();
        list.forEach(salary -> System.out.println(salary));

    }


    @Test
    public void deleteSalaryBySN() throws Exception {
        int n ;
        n = salaryDAO.deleteSalaryBySN("20015");
        assertEquals(n,n);
    }

    @Test
    public void addOneSalary() throws Exception {
        Date date = new Date();
        Salary salary = new Salary("1111",2.0,3.0,4.0,4.0,5.0,
                6.0,7.0,new java.sql.Date(date.getTime()));
        int n = salaryDAO.addOneSalary(salary);
        assertEquals(1,n);
    }


    @Test
    public void getOneSalary() throws Exception {
        Salary salary = salaryDAO.getOneSalary("20010");
        System.out.println(salary);
    }

    @Test
    public void getAllpersonSalary() throws Exception {
        List<Salary> list = salaryDAO.getAllpersonSalary("20010");
        list.forEach(salary -> System.out.println(salary));
    }


    @Test
    public void getPersonalSalaryTime() throws Exception {
        List<String> list = salaryDAO.getPersonalSalaryTime("20010");
        list.forEach(s -> System.out.println(s));
    }

}