package com.company.dao;



import com.company.entity.Department;

import java.sql.SQLException;
import java.util.List;

/**

 * 部门的接口
 */
public interface DepartmentDAO {

    /**
     * 根据部门编号得到一个部门信息
     * @param deptNumber
     * @return
     * @throws SQLException
     */
    Department getOneDept(String deptNumber) throws SQLException;

    /**
     * 得到所有的部门信息
     * @return
     * @throws SQLException
     */
    List<Department> getAll() throws SQLException;

    /**
     *
     * @param deptName
     * @return
     * @throws SQLException
     */
    Department getOneDeptByName(String deptName) throws SQLException;
}
