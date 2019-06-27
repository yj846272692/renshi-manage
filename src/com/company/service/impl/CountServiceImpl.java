package com.company.service.impl;



import com.company.dao.CountDAO;
import com.company.factory.DAOFactory;
import com.company.entity.EveryTimeSalaryCount;
import com.company.entity.PersonalCheckCount;
import com.company.entity.PersonalSalaryCount;
import com.company.entity.TypeCheckCount;
import com.company.service.CountService;

import java.sql.SQLException;
import java.util.List;


public class CountServiceImpl implements CountService {
    private CountDAO CountDAO = DAOFactory.getCountDAOInstance();
    @Override
    public List<PersonalCheckCount> getCheckCountByType(String staffNumber) {
        List<PersonalCheckCount> list = null;
        try {
            list = CountDAO.getCheckCountByType(staffNumber);
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
    public List<TypeCheckCount> getCountBycheckType() {
        List<TypeCheckCount> list = null;
        try {
            list = CountDAO.getCountBycheckType();
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
    public List<PersonalSalaryCount> getPersonalSalaryCountByType(String staffNumber,String time) {
        List<PersonalSalaryCount> list = null;
        try {
            list = CountDAO.getPersonalSalaryCountByType(staffNumber,time);
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
    public List<EveryTimeSalaryCount> getPersonalEveryTimeCount(String staffNumber) {
        List<EveryTimeSalaryCount> list = null;
        try {
            list = CountDAO.getPersonalEveryTimeCount(staffNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list.size()!=0){
            return list;
        }else {
            return null;
        }
    }
}