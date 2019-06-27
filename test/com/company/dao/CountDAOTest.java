package com.company.dao;

import com.company.factory.DAOFactory;
import com.company.entity.EveryTimeSalaryCount;
import com.company.entity.PersonalSalaryCount;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class CountDAOTest {


    private CountDAO countDAO;
    @Before
    public void setUp() throws Exception {
        countDAO = DAOFactory.getCountDAOInstance();
    }

    @Test
    public void getCheckCountByType() throws Exception {

    }

    @Test
    public void getCountBycheckType() throws Exception {

    }

    @Test
    public void getPersonalSalaryCountByType() throws Exception {
        List<PersonalSalaryCount> counts = countDAO.getPersonalSalaryCountByType("20010","2017-12-26");
        counts.forEach(count -> System.out.println(count));
    }

    @Test
    public void getPersonalEveryTimeCount() throws Exception {
        List<EveryTimeSalaryCount> list = countDAO.getPersonalEveryTimeCount("20010");
        list.forEach(count-> System.out.println(count));
    }

}