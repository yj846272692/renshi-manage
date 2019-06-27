package com.company.dao;


import com.company.entity.Check;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**

 * 考勤的接口
 */
public interface CheckDAO {

    /**
     * 得到所有的员工工号
     * @return
     * @throws SQLException
     */
    List<String> getAllStaffNumber() throws SQLException;

    /**
     * 根据员工工号，生成一个Map
     * @param staffNumber
     * @return
     * @throws SQLException
     */
    Map<String,Integer> makeOneCheckStatistics(String staffNumber) throws SQLException;


    /**
     * 生成所有的员工考勤统计记录
     * @param numberList
     * @return
     * @throws SQLException
     */
    Map<String,Map<String,Integer>> makeAllCheckStatistics(List<String> numberList) throws SQLException;


    /**
     * 得到所有的考勤记录
     * @return
     * @throws SQLException
     */
    List<Check> getAllCheck() throws SQLException;

    /**
     * 根据工号得到个人的考勤记录
     * @param staffNumber
     * @return
     * @throws SQLException
     */
    List<Check> getAllCheckBySN(String staffNumber) throws SQLException;


    /**
     * 增加一个考勤记录
     * @param check
     * @return
     * @throws SQLException
     */
    int addOneCheck(Check check) throws SQLException;


    /**
     * 根据工号，删除与本人有关的考勤记录
     * @param staffNumber
     * @return
     * @throws SQLException
     */
    int deleteCheckBySN(String staffNumber) throws SQLException;
}
