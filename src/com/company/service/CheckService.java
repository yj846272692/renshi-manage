package com.company.service;



import com.company.entity.Check;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**

 * 考勤服务接口
 */
public interface CheckService {

    /**
     * 得到所有的考勤信息
     * @return
     */
    List<Check> getAllCheck();

    /**
     * 根据工号得到本人的考勤信息
     * @param staffNumber
     * @return
     */
    List<Check> getAllCheckBySN(String staffNumber);

    /**
     * 增加一个考勤信息
     * @param check
     * @return
     */
    int addOneCheck(Check check);

    /**
     * 得到所有的员工工号
     * @return
     */
    List<String> getAllStaffNumber();

    /**
     * 根据员工工号，生成一个Map
     * @param staffNumber
     * @return
     * @throws SQLException
     */
    Map<String,Integer> makeOneCheckStatistics(String staffNumber);


    /**
     * 生成所有的员工考勤统计记录
     * @param numberList
     * @return
     * @throws SQLException
     */
    Map<String,Map<String,Integer>> makeAllCheckStatistics(List<String> numberList);

    /**
     * 删除与本人有关的考勤记录
     * @param staffNumber
     * @return
     */
    int deleteCheckBySN(String staffNumber);


}
