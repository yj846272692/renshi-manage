package com.company.service;


import com.company.entity.Department;

import java.sql.SQLException;
import java.util.List;

/**

 * 部门服务接口
 */
public interface DepartmentService {

    /**
     * 根据部门编号得到一个部门信息
     * @param deptNumber
     * @return
     */
    Department getOneDept(String deptNumber);


    /**
     * 得到所有的部门信息
     * @return
     * @throws SQLException
     */
    List<Department> getAll();

    /**
     *
     * @param deptName
     * @return
     * @throws SQLException
     */
    Department getOneDeptByName(String deptName);
}
