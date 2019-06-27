package com.company.dao.impl;



import com.company.dao.SalaryDAO;
import com.company.entity.Salary;
import com.company.utils.JDBCUtil;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**

 * 工资DAO的实现类
 */
public class SalaryDAOImpl implements SalaryDAO {

    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
    @Override
    public List<Salary> getAllSalary() throws SQLException {
        String sql = "SELECT * FROM t_salary ";
        List<Object> list = jdbcUtil.executeQuery(sql,null);
        List<Salary> salaries = new ArrayList<>();
        for (Object object : list){
            Map<String,Object> map = (Map<String, Object>) object;
            Salary salary = new Salary(
                    map.get("staffNumber").toString(),
                    (Double) map.get("basicSalary"),
                    (Double)map.get("bfSalary"),
                    (Double)map.get("deductSalary"),
                    (Double)map.get("personalTax"),
                    (Double)map.get("socialSec"),
                    (Double)map.get("reservedFunds"),
                    (Double)map.get("finalSalary"),
                    (Date)map.get("time"));
            salaries.add(salary);
        }
        return salaries;
    }

    @Override
    public int deleteSalaryBySN(String staffNumber) throws SQLException {
        int n;
        String sql = "DELETE FROM t_salary WHERE staffNumber = ? ";
        n = jdbcUtil.executeUpdate(sql,new Object[]{staffNumber});
        return n;
    }

    @Override
    public int addOneSalary(Salary salary) throws SQLException {
        int n;
        String sql = "INSERT INTO t_salary VALUES(null,?,?,?,?,?,?,?,?,?) ";
        Object[] params = {salary.getStaffNumber(),salary.getBasicSalary(),salary.getBfSalary(),
                salary.getDeductSalary(),salary.getPersonalTax(),salary.getSocialSec(),salary.getReservedFunds(),salary.getFinalSalary(),salary.getTime()};
        n = jdbcUtil.executeUpdate(sql,params);
        return n;
    }

    @Override
    public Salary getOneSalary(String staffNumber) throws SQLException {
        String sql = "SELECT * FROM t_salary WHERE staffNumber = ? ";
        Object[] params = {staffNumber};
        Map<String,Object> map = jdbcUtil.executeQuerySingle(sql,params);
        Salary salary = new Salary(
                map.get("staffNumber").toString(),
                (Double) map.get("basicSalary"),
                (Double)map.get("bfSalary"),
                (Double)map.get("deductSalary"),
                (Double)map.get("personalTax"),
                (Double)map.get("socialSec"),
                (Double)map.get("reservedFunds"),
                (Double)map.get("finalSalary"),
                (Date)map.get("time"));
        return salary;
    }

    @Override
    public List<Salary> getAllpersonSalary(String staffNumber) throws SQLException {
        String sql = "SELECT * FROM t_salary WHERE staffNumber = ? ";
        List<Object> list = jdbcUtil.executeQuery(sql,new Object[]{staffNumber});
        List<Salary> salaries = new ArrayList<>();
        for (Object object : list){
            Map<String,Object> map = (Map<String, Object>) object;
            Salary salary = new Salary(
                    map.get("staffNumber").toString(),
                    (Double) map.get("basicSalary"),
                    (Double)map.get("bfSalary"),
                    (Double)map.get("deductSalary"),
                    (Double)map.get("personalTax"),
                    (Double)map.get("socialSec"),
                    (Double)map.get("reservedFunds"),
                    (Double)map.get("finalSalary"),
                    (Date)map.get("time"));
            salaries.add(salary);
        }
        return salaries;
    }

    @Override
    public List<String> getPersonalSalaryTime(String staffNumber) throws SQLException {
        String sql = "SELECT t1.time FROM t_salary t1 WHERE staffNumber = ? ";
        List<Object> list = jdbcUtil.executeQuery(sql,new Object[]{staffNumber});
        List<String>  stringList = new ArrayList<>();
        for (Object object : list){
            Map<String,Object> map = (Map<String, Object>) object;
            String time = map.get("time").toString();
            stringList.add(time);
        }
        return stringList;
    }
}