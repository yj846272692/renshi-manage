package com.company.dao;

import com.company.factory.DAOFactory;
import com.company.entity.Staff;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;


public class StaffDAOTest {



    private StaffDAO staffDAO;
    @Before
    public void setUp() throws Exception {
        staffDAO = DAOFactory.getStaffDAOInstance();
    }

    @Test
    public void getOneStaff() throws Exception {
        Staff staff = staffDAO.getOneStaff("20010");
        System.out.println(staff);
    }

    @Test
    public void deleteOneStaff() throws Exception {
        int n;
        n = staffDAO.deleteOneStaff("20015");
        assertEquals(1,n);
    }

    @Test
    public void getAllPostName() throws Exception {
        List<String> list = staffDAO.getAllPostName();
        list.forEach(s -> System.out.println(s));
    }

}