package com.company.service.impl;



import com.company.dao.CheckStatisticsDAO;
import com.company.factory.DAOFactory;
import com.company.entity.CheckStatistics;
import com.company.service.CheckStatisticsService;

import java.sql.SQLException;
import java.util.List;

/**

 * 考勤统计服务接口实现类
 */
public class CheckStatisticsServiceImpl implements CheckStatisticsService {
    private CheckStatisticsDAO dao = DAOFactory.getCheckStatisticsDAOInstance();
    @Override
    public int addOneCheckSta(CheckStatistics checkStatistics) {
        int n = 0;
        try {
            n = dao.addOneCheckSta(checkStatistics);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public int deleteCheckSta(String staffNumber) {
        int n = 0;
        try {
            n = dao.deleteCheckSta(staffNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<CheckStatistics> getAllCheckSta() {
        List<CheckStatistics> list = null;
        try {
            list = dao.getAllCheckSta();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list.size() != 0){
            return list;
        }else {
            return null;
        }
    }
}