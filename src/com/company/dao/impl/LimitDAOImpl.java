package com.company.dao.impl;



import com.company.dao.LimitDAO;
import com.company.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限DAO的实现类
 */
public class LimitDAOImpl implements LimitDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
    @Override
    public List<String> getGroupName(String staffNumber) throws SQLException {
        String sql = "SELECT DISTINCT t3.groupName FROM t_limit_group t3 WHERE t3.groupID IN (SELECT t2.groupID FROM t_limit_map t1,t_limit_item t2 WHERE t1.staffNumber = ? AND t1.itemID = t2.itemID) ";
        List<Object> list = jdbcUtil.executeQuery(sql,new Object[]{staffNumber});
        List<String> strings = new ArrayList<>();
        for (Object object : list){
            Map<String,Object> map = (Map<String, Object>) object;
            String string = new String(map.get("groupName").toString());
            strings.add(string);
        }
        return strings;
    }

    @Override
    public Map<String,List<String>> getItemName(List<String> groupName,String staffNumber) throws SQLException {
        String sql = "SELECT t1.itemName FROM t_limit_item t1,t_limit_map t2,t_limit_group t3 WHERE t1.itemID = t2.itemID AND t2.staffNumber = ? AND t3.groupName = ? AND t1.groupID = t3.groupID ";
        Map<String,List<String>> listMap = new HashMap<>();
        for (int i=0;i<groupName.size();i++){
            List<Object> list = jdbcUtil.executeQuery(sql,new Object[]{staffNumber,groupName.get(i)});
            List<String> itemNames = new ArrayList<>();
            for (Object object: list){
                Map<String,Object> map = (Map<String, Object>) object;
                String string = new String(map.get("itemName").toString());
                itemNames.add(string);
            }
            listMap.put(groupName.get(i),itemNames);
        }
        return listMap;
    }

    @Override
    public Map<String, String> getItemIDAndName(String staffNumber) throws SQLException {
        String sql = " SELECT t2.`itemName`,t1.`itemID` FROM t_limit_map t1,t_limit_item t2 WHERE t1.`itemID` = t2.`itemID` AND t1.`staffNumber` = ? " ;
        List<Object> list = jdbcUtil.executeQuery(sql,new Object[]{staffNumber});
        Map<String,String> maps = new HashMap<>();
        for (Object object : list){
            Map<String,Object> map = (Map<String, Object>) object;
            String itemName = map.get("itemName").toString();
            String itemID = map.get("itemID").toString();
            maps.put(itemName,itemID);
        }
        return maps;
    }
}