package com.company.service.impl;


import com.company.dao.LimitMapDAO;
import com.company.factory.DAOFactory;
import com.company.entity.LimitMap;
import com.company.service.LimitMapService;

import java.sql.SQLException;

public class LimitMapServiceImpl implements LimitMapService {
    private LimitMapDAO limitMapDAO = DAOFactory.getLimitMapDAOInstance();
    @Override
    public int addOneLimitMap(LimitMap limitMap) {
        int n=0;
        try {
            n = limitMapDAO.addOneLimitMap(limitMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public int deleteOneLimitMap(String staffNumber, String itemID) {
        int n =0;
        try {
            n = limitMapDAO.deleteOneLimitMap(staffNumber,itemID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
}