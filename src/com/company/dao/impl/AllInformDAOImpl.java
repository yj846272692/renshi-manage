package com.company.dao.impl;



import com.company.dao.AllInformDAO;
import com.company.entity.AllInform;
import com.company.utils.JDBCUtil;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**

 * 全体通知DAO的实现类
 */
public class AllInformDAOImpl implements AllInformDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
    @Override
    public List<AllInform> getAllInform() throws SQLException {
        String sql = "SELECT * FROM t_all_inform ";
        List<Object> list = jdbcUtil.executeQuery(sql,null);
        List<AllInform> informs = new ArrayList<>();
        for (Object object : list){
            Map<String,Object> map = (Map<String, Object>) object;
            AllInform inform = new AllInform(
                    (Integer) map.get("id"),
                    map.get("senderNumber").toString(),
                    map.get("informTitle").toString(),
                    map.get("informContent").toString(),
                    (Date)map.get("sendDate"));
            informs.add(inform);
        }
        return informs;
    }

    @Override
    public int addOneInform(AllInform inform) throws SQLException {
        int n;
        String sql = "INSERT INTO t_all_inform  VALUES(null,?,?,?,?) ";
        Object[] params = {inform.getSenderNumber(),inform.getInformTitle(),inform.getInformContent(),inform.getSendDate()};
        n = jdbcUtil.executeUpdate(sql,params);
        return n;
    }
}