package com.company.dao.impl;



import com.company.dao.LimitGroupDAO;
import com.company.entity.LimitGroup;
import com.company.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**

 * 权限组的实现类
 */
public class LimitGroupDAOImpl implements LimitGroupDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
    @Override
    public LimitGroup getLimitGroup(String groupID) throws SQLException {
        String sql = "SELECT * FROM t_limit_group WHERE groupID  = ? ";
        Map<String , Object> map = jdbcUtil.executeQuerySingle(sql,new Object[]{groupID});
        LimitGroup limitGroup = new LimitGroup(map.get("groupID").toString(),map.get("groupName").toString());
        return limitGroup;
    }

    @Override
    public List<LimitGroup> getAllLimitGroup() throws SQLException {
        String sql = "SELECT * FROM t_limit_group ";
        List<Object> list = jdbcUtil.executeQuery(sql,null);
        List<LimitGroup> limitGroups = new ArrayList<>();
        for (Object object : list){
            Map<String,Object> map = (Map<String, Object>) object;
            LimitGroup limitGroup = new LimitGroup(map.get("groupID").toString(),map.get("groupName").toString());
            limitGroups.add(limitGroup);
        }
        return limitGroups;
    }
}