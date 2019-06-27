package com.company.service;



import com.company.entity.Staff;

import java.sql.SQLException;
import java.util.List;

/**
 * 员工服务接口
 */
public interface StaffService {

    /**
     * 根据工号得到一个员工的信息
     * @param staffNumber
     * @return
     */
    Staff getOndStaff(String staffNumber);

    /**
     * 根据工号查找除本人以外的所有员工
     * @param staffNumber
     * @return
     */
    List<Staff> getAll(String staffNumber);


    /**
     * 得到所有的员工信息
     * @return
     */
    List<Staff> getAll();

    /**
     * 得到所有的员工职称
     * @return
     * @throws SQLException
     */
    List<String> getAllPostName();


    /**
     * 更新一个员工的信息
     * @param staff
     * @return
     * @throws SQLException
     */
    int updateOneStaff(Staff staff);


    /**
     * 增加一个员工
     * @param staff
     * @return
     */
    int addOneStaff(Staff staff);


    /**
     * 删除一个员工
     * @param staffNumber
     * @return
     */
    int deleteOneStaff(String staffNumber);
}
