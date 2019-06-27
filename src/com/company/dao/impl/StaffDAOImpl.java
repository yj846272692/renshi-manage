package com.company.dao.impl;



import com.company.dao.StaffDAO;
import com.company.entity.Staff;
import com.company.utils.JDBCUtil;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 员工DAO的实现类
 */
public class StaffDAOImpl implements StaffDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
    @Override
    public Staff getOneStaff(String staffNumber) throws SQLException {
        String sql = "SELECT * FROM t_staff WHERE staffNumber =? ";
        Map<String ,Object> map = jdbcUtil.executeQuerySingle(sql,new Object[]{staffNumber});
        Staff staff = new Staff(
                map.get("staffNumber").toString(),
                map.get("staffName").toString(),
                map.get("staffAge").toString(),
                map.get("staffSex").toString(),
                map.get("deptNumber").toString(),
                map.get("staffPost").toString(),
                map.get("staffPlace").toString(),
                map.get("staffMarriage").toString(),
                map.get("staffPhone").toString(),
                map.get("staffMailbox").toString(),
                (byte[]) map.get("staffPicture"),
                (Date)map.get("staffEntrytime"));
        return staff;
    }

    @Override
    public List<Staff> getAll(String staffNumber) throws SQLException {
        String sql = "SELECT * FROM t_staff WHERE staffNumber NOT IN (?) ";
        List<Object> list = jdbcUtil.executeQuery(sql,new Object[]{staffNumber});
        List<Staff> staffList = new ArrayList<>();
        for (Object object : list){
            Map<String,Object> map = (Map<String, Object>) object;
            Staff staff = new Staff(map.get("staffNumber").toString(),
                    map.get("staffName").toString(),
                    map.get("staffAge").toString(),
                    map.get("staffSex").toString(),
                    map.get("deptNumber").toString(),
                    map.get("staffPost").toString(),
                    map.get("staffPlace").toString(),
                    map.get("staffMarriage").toString(),
                    map.get("staffPhone").toString(),
                    map.get("staffMailbox").toString(),
                    (byte[]) map.get("staffPicture"),
                    (Date)map.get("staffEntrytime"));
            staffList.add(staff);
        }
        return staffList;
    }

    @Override
    public List<Staff> getAll() throws SQLException {
        String sql = "SELECT * FROM t_staff ";
        List<Object> list = jdbcUtil.executeQuery(sql,null);
        List<Staff> staffList = new ArrayList<>();
        for (Object object : list){
            Map<String,Object> map = (Map<String, Object>) object;
            Staff staff = new Staff(map.get("staffNumber").toString(),
                    map.get("staffName").toString(),
                    map.get("staffAge").toString(),
                    map.get("staffSex").toString(),
                    map.get("deptNumber").toString(),
                    map.get("staffPost").toString(),
                    map.get("staffPlace").toString(),
                    map.get("staffMarriage").toString(),
                    map.get("staffPhone").toString(),
                    map.get("staffMailbox").toString(),
                    (byte[]) map.get("staffPicture"),
                    (Date)map.get("staffEntrytime"));
            staffList.add(staff);
        }
        return staffList;
    }

    @Override
    public List<String> getAllPostName() throws SQLException {
        String sql = "SELECT DISTINCT t1.`staffPost` FROM t_staff t1 ";
        List<Object> list = jdbcUtil.executeQuery(sql,null);
        List<String> stringList = new ArrayList<>();
        for (Object object : list){
            Map<String,Object> map = (Map<String, Object>) object;
            String postName = map.get("staffPost").toString();
            stringList.add(postName);
        }
        return stringList;
    }

    @Override
    public int updateOneStaff(Staff staff) throws SQLException {
        int n;
        String sql = "UPDATE t_staff SET staffPhone = ? , staffMailbox = ? WHERE staffNumber = ? ";
        Object[] params = {staff.getStaffPhone(),staff.getStaffMailbox(),staff.getStaffNumber()};
        n = jdbcUtil.executeUpdate(sql,params);
        return n;
    }

    @Override
    public int addOneStaff(Staff staff) throws SQLException {
        int n ;
        String sql = "INSERT INTO t_staff VALUES(?,?,?,?,?,?,?,?,?,?,?,?) ";
        Object[] params = {
                staff.getStaffNumber(),
                staff.getStaffName(),
                staff.getStaffAge(),
                staff.getStaffSex(),
                staff.getDeptNumber(),
                staff.getStaffPost(),
                staff.getStaffPlace(),
                staff.getStaffMarriage(),
                staff.getStaffPhone(),
                staff.getStaffMailbox(),
                staff.getStaffPicture(),
                staff.getStaffEntrytime()};
        n = jdbcUtil.executeUpdate(sql,params);
        return n;
    }

    @Override
    public int deleteOneStaff(String staffNumber) throws SQLException {
        int n;
        String sql = "DELETE FROM t_staff WHERE staffNumber = ? ";
        Object[] params = {staffNumber};
        n = jdbcUtil.executeUpdate(sql,params);
        return n;
    }
}