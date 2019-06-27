package com.company.service.impl;

import com.company.dao.LimitItemDAO;
import com.company.factory.DAOFactory;
import com.company.service.LimitItemService;

import java.sql.SQLException;


public class LimitItemServiceImpl implements LimitItemService {
    private LimitItemDAO dao = DAOFactory.getLimitItemDAOInstance();
    @Override
    public String getLimitItemID(String itemName) {
        String itemID = null;
        try {
            itemID = dao.getLimitItemID(itemName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (itemID != null){
            return itemID;
        }else {
            return null;
        }
    }
}