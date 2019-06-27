package com.company.dao.impl;

import com.company.dao.CheckDAO;
import com.company.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class CheckDAOImplTest {
    private CheckDAO checkDAO = DAOFactory.getCheckDAOInstance();

    @Test
    public void getAllCheck() {
        try {
            System.out.println(checkDAO.getAllCheck());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}