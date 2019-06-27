package com.company.service.impl;



import com.company.dao.StaffDAO;
import com.company.factory.DAOFactory;
import com.company.entity.Staff;
import com.company.service.StaffService;

import java.sql.SQLException;
import java.util.List;

/**

 * 员工服务实现类
 */
public class StaffServiceImpl implements StaffService {
    private StaffDAO staffDAO = DAOFactory.getStaffDAOInstance();
    @Override
    public Staff getOndStaff(String staffNumber) {
        Staff staff = null;
        try {
            staff = staffDAO.getOneStaff(staffNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staff;
    }

    @Override
    public List<Staff> getAll(String staffNumber) {
        List<Staff> list = null;
        try {
            list = staffDAO.getAll(staffNumber);
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
    public List<Staff> getAll() {
        List<Staff> list = null;
        try {
            list = staffDAO.getAll();
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
    public List<String> getAllPostName() {
        List<String> list = null;
        try {
            list = staffDAO.getAllPostName();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list.size() != 0 ){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public int updateOneStaff(Staff staff) {
        int n =0;
        try {
            n = staffDAO.updateOneStaff(staff);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public int addOneStaff(Staff staff) {
        int n =0;
        try {
            n = staffDAO.addOneStaff(staff);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public int deleteOneStaff(String staffNumber) {
        int n = 0;
        try {
            n = staffDAO.deleteOneStaff(staffNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
}