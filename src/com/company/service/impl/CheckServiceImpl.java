package com.company.service.impl;



import com.company.dao.CheckDAO;
import com.company.factory.DAOFactory;
import com.company.entity.Check;
import com.company.service.CheckService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**

 * 考勤服务实现类
 */
public class CheckServiceImpl implements CheckService {
    private CheckDAO checkDAO = DAOFactory.getCheckDAOInstance();
    @Override
    public List<Check> getAllCheck() {
        List<Check> checkList = null;
        try {
            checkList = checkDAO.getAllCheck();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (checkList.size() != 0){
            return checkList;
        }else {
            return null;
        }
    }

    @Override
    public List<Check> getAllCheckBySN(String staffNumber) {
        List<Check> checkList = null;
        try {
            checkList = checkDAO.getAllCheckBySN(staffNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (checkList.size() != 0){
            return checkList;
        }else {
            return null;
        }
    }

    @Override
    public int addOneCheck(Check check) {
        int n = 0;
        try {
            n = checkDAO.addOneCheck(check);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<String> getAllStaffNumber() {
        List<String> list = null;
        try {
            list = checkDAO.getAllStaffNumber();
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
    public Map<String, Integer> makeOneCheckStatistics(String staffNumber) {
        Map<String, Integer> map = null;
        try {
            map = checkDAO.makeOneCheckStatistics(staffNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (map.size() != 0){
            return map;
        }else {
            return null;
        }
    }

    @Override
    public Map<String, Map<String, Integer>> makeAllCheckStatistics(List<String> numberList) {
        Map<String, Map<String, Integer>> mapMap = null;
        try {
            mapMap = checkDAO.makeAllCheckStatistics(numberList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (mapMap.size() != 0){
            return mapMap;
        }else {
            return null;
        }
    }

    @Override
    public int deleteCheckBySN(String staffNumber) {
        int n = 0;
        try {
            n = checkDAO.deleteCheckBySN(staffNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
}