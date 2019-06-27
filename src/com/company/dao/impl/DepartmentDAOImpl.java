package com.company.dao.impl;



import com.company.dao.DepartmentDAO;
import com.company.entity.Department;
import com.company.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**

 * 部门DAO的实现类
 */
public class DepartmentDAOImpl implements DepartmentDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
    @Override
    public Department getOneDept(String deptNumber) throws SQLException {
        String sql = "SELECT * FROM t_department WHERE deptNumber = ? ";
        Map<String,Object> map = jdbcUtil.executeQuerySingle(sql,new Object[]{deptNumber});
        Department department = new Department(map.get("deptNumber").toString(),map.get("deptName").toString());
        return department;
    }

    @Override
    public List<Department> getAll() throws SQLException {
        String sql = "SELECT * FROM t_department ";
        List<Object> list = jdbcUtil.executeQuery(sql,null);
        List<Department> departments = new ArrayList<>();
        for (Object object : list){
            Map<String,Object> map = (Map<String, Object>) object;
            Department department = new Department(map.get("deptNumber").toString(),map.get("deptName").toString());
            departments.add(department);
        }
        return departments;
    }

    @Override
    public Department getOneDeptByName(String deptName) throws SQLException {
        String sql = "SELECT * FROM t_department WHERE deptName = ? ";
        Map<String,Object> map = jdbcUtil.executeQuerySingle(sql,new Object[]{deptName});
        Department department = new Department(map.get("deptNumber").toString(),map.get("deptName").toString());
        return department;
    }
}