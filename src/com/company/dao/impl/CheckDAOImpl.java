package com.company.dao.impl;



import com.company.dao.CheckDAO;
import com.company.entity.Check;
import com.company.utils.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**

 * 考勤DAO的实现类
 */
public class CheckDAOImpl implements CheckDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();

    @Override
    public List<String> getAllStaffNumber() throws SQLException {
        String sql = "SELECT DISTINCT staffNumber FROM t_check ";
        List<Object> list = jdbcUtil.executeQuery(sql,null);
        List<String> strings = new ArrayList<>();
        for (Object object : list){
            Map<String,Object> map = (Map<String, Object>) object;
            String string = new String(map.get("staffNumber").toString());
            strings.add(string);
        }
        return strings;
    }

    @Override
    public Map<String, Integer> makeOneCheckStatistics(String staffNumber) throws SQLException {
        String sql = "SELECT t1.checkType,COUNT(*) AS number FROM t_check t1 WHERE t1.staffNumber = ? GROUP BY t1.checkType ";
        Object[] params = {staffNumber};
        List<Object> list = jdbcUtil.executeQuery(sql,params);
        Map<String,Integer> maps  = new HashMap<>();
        for (Object object : list){
            Map<String,Object> map = (Map<String, Object>) object;
            String type = map.get("checkType").toString();
            int count = Integer.parseInt(map.get("number").toString());
            maps.put(type,count);
        }
        return maps;
    }

    @Override
    public Map<String, Map<String, Integer>> makeAllCheckStatistics(List<String> numberList) throws SQLException {
        String sql = "SELECT t1.checkType,COUNT(*) AS number FROM t_check t1 WHERE t1.staffNumber = ? GROUP BY t1.checkType ";
        Map<String, Map<String, Integer>> mapMap = new HashMap<>();
        for (int i=0;i<numberList.size();i++){
            List<Object> list = jdbcUtil.executeQuery(sql,new Object[]{numberList.get(i)});
            Map<String,Integer> maps  = new HashMap<>();
            for (Object object : list){
                Map<String,Object> map = (Map<String, Object>) object;
                String type = map.get("checkType").toString();
                int count = Integer.parseInt(map.get("number").toString());
                maps.put(type,count);
            }
            mapMap.put(numberList.get(i),maps);
        }
        return mapMap;
    }

    @Override
    public List<Check> getAllCheck() throws SQLException {
        String sql = "SELECT * FROM  t_check ";
        List<Object> list = jdbcUtil.executeQuery(sql,null);
        List<Check> checks = new ArrayList<>();
        for (Object object: list){
            Map<String,Object> map = (Map<String, Object>) object;
            Check check = new Check(map.get("staffNumber").toString(),map.get("checkType").toString(),(Date) map.get("checkDate"));
            checks.add(check);
        }
        return checks;
    }

    @Override
    public List<Check> getAllCheckBySN(String staffNumber) throws SQLException {
        String sql = "SELECT * FROM t_check WHERE staffNumber = ?  ";
        List<Object> list = jdbcUtil.executeQuery(sql,new Object[]{staffNumber});
        List<Check> checks = new ArrayList<>();
        for (Object object: list){
            Map<String,Object> map = (Map<String, Object>) object;
            Check check = new Check(map.get("staffNumber").toString(),map.get("checkType").toString(),(Date) map.get("checkDate"));
            checks.add(check);
        }
        return checks;
    }

    @Override
    public int addOneCheck(Check check) throws SQLException {
        int n;
        String sql = "INSERT INTO t_check VALUES(null,?,?,?) ";
        Object[] params = {check.getStaffNumber(),check.getCheckType(),check.getCheckDate()};
        n = jdbcUtil.executeUpdate(sql,params);
        return n;
    }

    @Override
    public int deleteCheckBySN(String staffNumber) throws SQLException {
        int n;
        String sql = "DELETE FROM t_check WHERE staffNumber = ? ";
        n = jdbcUtil.executeUpdate(sql,new Object[]{staffNumber});
        return n;
    }
}