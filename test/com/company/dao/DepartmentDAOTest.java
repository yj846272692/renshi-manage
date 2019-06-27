package com.company.dao;

import com.company.factory.DAOFactory;
import com.company.entity.Department;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class DepartmentDAOTest {


    private DepartmentDAO departmentDAO;
    @Before
    public void setUp() throws Exception {
        departmentDAO = DAOFactory.getDepartmentDAOInstance();
    }

    @Test
    public void getOneDept() throws Exception {
        Department department = departmentDAO.getOneDept("1");
        System.out.println(department);
    }

    @Test
    public void getAll() throws Exception {
        List<Department> list = departmentDAO.getAll();
        list.forEach(department -> System.out.println(department));
    }

}