package com.company.service.impl;



import com.company.dao.DepartmentDAO;
import com.company.factory.DAOFactory;
import com.company.entity.Department;
import com.company.service.DepartmentService;

import java.sql.SQLException;
import java.util.List;

/**

 * 部门服务实现类
 */
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDAO departmentDAO = DAOFactory.getDepartmentDAOInstance();
    @Override
    public Department getOneDept(String deptNumber) {
        Department department = null;
        try {
            department = departmentDAO.getOneDept(deptNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (department != null){
            return department;
        }else {
            return null;
        }
    }

    @Override
    public List<Department> getAll() {
        List<Department> list = null;
        try {
            list = departmentDAO.getAll();
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
    public Department getOneDeptByName(String deptName) {
        Department department = null;
        try {
            department = departmentDAO.getOneDeptByName(deptName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (department != null){
            return department;
        }else {
            return null;
        }
    }
}