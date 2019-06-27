package com.company.service.impl;



import com.company.dao.SalaryDAO;
import com.company.factory.DAOFactory;
import com.company.entity.Salary;
import com.company.service.SalaryService;

import java.sql.SQLException;
import java.util.List;

/**

 * 工资详情服务实现类
 */
public class SalaryServiceImpl implements SalaryService {
    private SalaryDAO salaryDAO = DAOFactory.getSalaryDAOInstance();
    @Override
    public List<Salary> getAllSalary() {
        List<Salary> list = null;
        try {
            list = salaryDAO.getAllSalary();
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
    public int deleteSalaryBySN(String staffNumber) {
        int n = 0;
        try {
            n = salaryDAO.deleteSalaryBySN(staffNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public int addOneSalary(Salary salary) {
        int n=0;
        try {
            n = salaryDAO.addOneSalary(salary);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public Salary getOneSalary(String staffNumber) {
        Salary salary = null;
        try {
            salary = salaryDAO.getOneSalary(staffNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (salary != null){
            return salary;
        }else {
            return null;
        }
    }

    @Override
    public List<Salary> getAllpersonSalary(String staffNumber) {
        List<Salary> list = null;
        try {
            list = salaryDAO.getAllpersonSalary(staffNumber);
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
    public List<String> getPersonalSalaryTime(String staffNumber) {
        List<String> list = null;
        try {
            list = salaryDAO.getPersonalSalaryTime(staffNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list.size() != 0){
            return list;
        }else {
            return null;
        }
    }
}