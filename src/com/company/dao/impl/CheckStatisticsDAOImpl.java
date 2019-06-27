package com.company.dao.impl;


import com.company.dao.CheckStatisticsDAO;
import com.company.entity.CheckStatistics;
import com.company.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**

 * 考勤统计接口实现类
 */
public class CheckStatisticsDAOImpl implements CheckStatisticsDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
    @Override
    public int addOneCheckSta(CheckStatistics check) throws SQLException {
        int n ;
        String sql = "INSERT INTO t_check_statistics VALUES(null,?,?,?,?,?,?) ";
        Object[] params = {check.getStaffNumber(),check.getNormalCount(),check.getTruantCount(),check.getLateCount(),check.getLeaveCount(),check.getHolidayCount()};
        n = jdbcUtil.executeUpdate(sql,params);
        return n;
    }

    @Override
    public int deleteCheckSta(String staffNumber) throws SQLException {
        int n;
        String sql = "DELETE FROM t_check_statistics WHERE staffNumber = ? ";
        n = jdbcUtil.executeUpdate(sql,new Object[]{staffNumber});
        return n;
    }

    @Override
    public List<CheckStatistics> getAllCheckSta() throws SQLException {
        String sql = "SELECT * FROM t_check_statistics ";
        List<Object> list = jdbcUtil.executeQuery(sql,null);
        List<CheckStatistics> statisticsList = new ArrayList<>();
        for (Object object : list){
            Map<String,Object> map = (Map<String, Object>) object;
            CheckStatistics statistics = new CheckStatistics(
                    map.get("staffNumber").toString(),
                    (Integer) map.get("normalCount"),
                    (Integer) map.get("truantCount"),
                    (Integer)map.get("lateCount"),
                    (Integer)map.get("leaveCount"),
                    (Integer)map.get("holidayCount"));
            statisticsList.add(statistics);
        }
        return statisticsList;
    }
}