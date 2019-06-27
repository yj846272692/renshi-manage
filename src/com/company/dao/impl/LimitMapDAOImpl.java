package com.company.dao.impl;



import com.company.dao.LimitMapDAO;
import com.company.entity.LimitMap;
import com.company.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.Map;

/**

 * 权限映射DAO的实现类
 */
public class LimitMapDAOImpl implements LimitMapDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
    @Override
    public LimitMap getLimitMap(String staffNumber) throws SQLException {
        String sql = "SELECT * FROM t_limit_map WHERE staffNumber = ? ";
        Map<String,Object> map = jdbcUtil.executeQuerySingle(sql,new Object[]{staffNumber});
        LimitMap limitMap = new LimitMap(map.get("staffNumber").toString(),map.get("itemID").toString());
        return limitMap;
    }

    @Override
    public int addOneLimitMap(LimitMap limitMap) throws SQLException {
        int n;
        String sql = "INSERT INTO t_limit_map VALUES(null,?,?) ";
        Object[] params = {limitMap.getStaffNumber(),limitMap.getItemID()};
        n = jdbcUtil.executeUpdate(sql,params);
        return n;
    }

    @Override
    public int deleteOneLimitMap(String staffNumber, String itemID) throws SQLException {
        int n;
        String sql = "DELETE FROM t_limit_map WHERE staffNumber = ? AND itemID = ? ";
        Object[] params = {staffNumber,itemID};
        n = jdbcUtil.executeUpdate(sql,params);
        return n;
    }
}