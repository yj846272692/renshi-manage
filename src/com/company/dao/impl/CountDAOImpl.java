package com.company.dao.impl;



import com.company.dao.CountDAO;
import com.company.factory.ServiceFactory;
import com.company.entity.EveryTimeSalaryCount;
import com.company.entity.PersonalCheckCount;
import com.company.entity.PersonalSalaryCount;
import com.company.entity.TypeCheckCount;
import com.company.service.CheckService;
import com.company.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**

 * 考勤统计实现类
 */
public class CountDAOImpl implements CountDAO {
    private CheckService checkService = ServiceFactory.getCheckServiceInstance();
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
    @Override
    public List<PersonalCheckCount> getCheckCountByType(String staffNumber) throws SQLException {
        Map<String,Integer> map = checkService.makeOneCheckStatistics(staffNumber);
        List<PersonalCheckCount> list = new ArrayList<>();
        for (Map.Entry<String,Integer> entry : map.entrySet()){
            PersonalCheckCount checkCount = new PersonalCheckCount(entry.getKey(),entry.getValue());
            list.add(checkCount);
        }
        return list;
    }

    @Override
    public List<TypeCheckCount> getCountBycheckType() throws SQLException {
        List<TypeCheckCount> list = new ArrayList<>();
        String sql = "SELECT checkType,COUNT(*) FROM t_check t1  GROUP BY t1.checkType";
        Connection connection = jdbcUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            String checkType = rs.getString(1);
            int n = rs.getInt(2);
            TypeCheckCount tcc = new TypeCheckCount(checkType,n);
            list.add(tcc);
        }
        return list;
    }

    @Override
    public List<PersonalSalaryCount> getPersonalSalaryCountByType(String staffNumber,String time) throws SQLException {
        String sql = "SELECT t1.basicSalary , t1.`bfSalary`,t1.`deductSalary`,t1.`personalTax`,t1.`socialSec`,t1.`reservedFunds`,t1.`finalSalary` FROM t_salary t1 WHERE t1.staffNumber = ? AND t1.time = ?  ";
        List<Object> list = jdbcUtil.executeQuery(sql,new Object[]{staffNumber,time});
        Map<String,Double> maps = new HashMap<>();
        for (Object object : list){
            Map<String,Object> map = (Map<String, Object>) object;
            double basicSalary = Double.parseDouble(map.get("basicSalary").toString());
            double bfSalary = Double.parseDouble(map.get("bfSalary").toString());
            double deductSalary = Double.parseDouble(map.get("deductSalary").toString());
            double personalTax = Double.parseDouble(map.get("personalTax").toString());
            double socialSec = Double.parseDouble(map.get("socialSec").toString());
            double reservedFunds = Double.parseDouble(map.get("reservedFunds").toString());
            double finalSalary = Double.parseDouble(map.get("finalSalary").toString());
            maps.put("基础工资",basicSalary);
            maps.put("补发工资",bfSalary);
            maps.put("应扣工资",deductSalary);
            maps.put("个人税收",personalTax);
            maps.put("社保",socialSec);
            maps.put("公积金",reservedFunds);
            maps.put("最终工资",finalSalary);
        }
        List<PersonalSalaryCount> personalSalaryCounts = new ArrayList<>();
        for (Map.Entry<String,Double> entry : maps.entrySet()){
            String salaryType = entry.getKey();
            Double count = entry.getValue();
            PersonalSalaryCount personalSalaryCount = new PersonalSalaryCount(salaryType,count);
            personalSalaryCounts.add(personalSalaryCount);
        }
        return personalSalaryCounts;
    }

    @Override
    public List<EveryTimeSalaryCount> getPersonalEveryTimeCount(String staffNumber) throws SQLException {
        String sql = "SELECT t1.`time` ,t1.`finalSalary` FROM t_salary t1 WHERE t1.`staffNumber`= ? ";
        Object[] params = {staffNumber};
        List<Object> list = jdbcUtil.executeQuery(sql,params);
        Map<String,Double> maps = new HashMap<>();
        for (Object object : list){
            Map<String,Object> map = (Map<String, Object>) object;
            String everyTime = map.get("time").toString();
            Double salaryCount = Double.parseDouble(map.get("finalSalary").toString());
            maps.put(everyTime,salaryCount);
        }
        List<EveryTimeSalaryCount> countList = new ArrayList<>();
        for (Map.Entry<String,Double> entry : maps.entrySet()){
            String everyTime = entry.getKey();
            Double salaryCount = entry.getValue();
            EveryTimeSalaryCount count = new EveryTimeSalaryCount(everyTime,salaryCount);
            countList.add(count);
        }
        return countList;
    }
}