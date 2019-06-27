package com.company.dao.impl;



import com.company.dao.LimitItemDAO;
import com.company.entity.LimitItem;
import com.company.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.Map;

/**

 * 权限项DAO的实现类
 */
public class LimitItemDAOImpl implements LimitItemDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
    @Override
    public LimitItem getLimitItem(String itemID) throws SQLException {
        String sql = "SELECT * FROM t_limit_item WHERE itemID = ? ";
        Map<String ,Object> map = jdbcUtil.executeQuerySingle(sql,new Object[]{itemID});
        LimitItem limitItem = new LimitItem(map.get("itemID").toString(),map.get("groupID").toString(),map.get("itemName").toString());
        return limitItem;
    }

    @Override
    public String getLimitItemID(String itemName) throws SQLException {
        String sql = "SELECT itemID FROM t_limit_item WHERE itemName = ? ";
        Object[] params = {itemName};
        Map<String,Object> map = jdbcUtil.executeQuerySingle(sql,params);
        String itemID = map.get("itemID").toString();
        return itemID;
    }
}