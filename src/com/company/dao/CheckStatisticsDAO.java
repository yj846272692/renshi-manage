package com.company.dao;



import com.company.entity.CheckStatistics;

import java.sql.SQLException;
import java.util.List;

/**
 * 考勤统计接口
 */
public interface CheckStatisticsDAO {

    /**
     * 增加一条考勤统计记录
     * @param checkStatistics
     * @return
     * @throws SQLException
     */
    int addOneCheckSta(CheckStatistics checkStatistics) throws SQLException;


    /**
     * 删除考勤统计
     * @param staffNumber
     * @return
     * @throws SQLException
     */
    int deleteCheckSta(String staffNumber) throws SQLException;

    /**
     * 得到所有的考勤统计记录
     * @return
     * @throws SQLException
     */
    List<CheckStatistics> getAllCheckSta() throws SQLException;
}
