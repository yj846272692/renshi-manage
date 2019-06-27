package com.company.dao.impl;

import com.company.dao.LoginDAO;
import com.company.entity.Login;
import com.company.factory.DAOFactory;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class LoginDAOImplTest {
    private LoginDAO loginDAO = DAOFactory.getLoginDAOInstance();

    private LoginDAO loginDAO1 = DAOFactory.getLogin();

    @Test
    public void getOneLogin() {
        try {
            Login login = loginDAO.getOneLogin("20000");
            System.out.println(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getLogin() {
        boolean login = Boolean.parseBoolean(null);
        try {
            loginDAO1.getLogin("D:/1.txt");
            System.out.println(login);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}