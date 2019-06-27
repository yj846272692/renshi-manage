package com.company.service.impl;

import com.company.dao.LimitDAO;
import com.company.factory.DAOFactory;
import com.company.service.LimitService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public class LimitServiceImpl implements LimitService {
    private LimitDAO limitDAO = DAOFactory.getLimitDAOInstance();
    @Override
    public List<String> getGroupName(String staffNumber) {
        List<String> list = null;
        try {
             list = limitDAO.getGroupName(staffNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list.size() != 0){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public Map<String, List<String>> getItemName(List<String> groupName, String staffNumber) {
        List<String> groupNameList = null;
        Map<String, List<String>> listMap = null;
        try {
            groupNameList = limitDAO.getGroupName(staffNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (groupNameList.size() != 0 ){
            try {
                listMap = limitDAO.getItemName(groupNameList,staffNumber);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (listMap.size() != 0 ){
                return listMap;
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    @Override
    public Map<String, String> getItemIDAndName(String staffNumber) {
        Map<String,String> map = null;
        try {
            map = limitDAO.getItemIDAndName(staffNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (map.size() != 0 ){
            return map;
        }else {
            return null;
        }
    }
}