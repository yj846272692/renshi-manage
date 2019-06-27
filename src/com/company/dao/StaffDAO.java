package com.company.dao;



import com.company.entity.Staff;

import java.sql.SQLException;
import java.util.List;

/**

 * 员工接口
 */
public interface StaffDAO {

    /**
     * 根据工号得到一个员工信息
     * @param staffNumber
     * @return
     * @throws SQLException
     */
    Staff getOneStaff(String staffNumber) throws SQLException;

    /**
     * 根据工号来查询除本人以外的其余全体员工
     * @param staffNumber
     * @return
     * @throws SQLException
     */
    List<Staff> getAll(String staffNumber) throws SQLException;

    /**
     * 得到所有的员工信息
     * @return
     * @throws SQLException
     */
    List<Staff> getAll() throws SQLException;


    /**
     * 得到所有的员工职称
     * @return
     * @throws SQLException
     */
    List<String> getAllPostName() throws SQLException;

    /**
     * 更新一个员工的信息
     * @param staff
     * @return
     * @throws SQLException
     */
    int updateOneStaff(Staff staff) throws SQLException;


    /**
     * 增加一个员工
     * @param staff
     * @return
     * @throws SQLException
     */
    int addOneStaff(Staff staff) throws SQLException;

    /**
     * 删除一个员工
     * @param staffNumber
     * @return
     * @throws SQLException
     */
    int deleteOneStaff(String staffNumber) throws SQLException;
}
