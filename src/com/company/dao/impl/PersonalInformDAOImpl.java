package com.company.dao.impl;



import com.company.dao.PersonalInformDAO;
import com.company.entity.PersonalInform;
import com.company.utils.JDBCUtil;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 个人私信DAO的实现类
 */
public class PersonalInformDAOImpl implements PersonalInformDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();

    @Override
    public int addOneInform(PersonalInform personalInform) throws SQLException {
        int n ;
        String sql = "INSERT INTO t_personal_inform(receiverNumber,senderNumber,informContent,sendDate) VALUES(?,?,?,?) ";
        Object[] params = {personalInform.getReceiverNumber(),personalInform.getSenderNumber(),personalInform.getInformContent(),personalInform.getSendDate()};
        n = jdbcUtil.executeUpdate(sql,params);
        return n;
    }

    @Override
    public PersonalInform getOneInform(Integer id, String receiverNumber) throws SQLException {
        String sql = "SELECT * FROM t_personal_inform WHERE id = ? AND receiverNumber = ? ";
        Object[] params = {id,receiverNumber};
        Map<String,Object> map = jdbcUtil.executeQuerySingle(sql,params);
        PersonalInform inform = new PersonalInform(
                (Integer) map.get("id"),
                map.get("receiverNumber").toString(),
                map.get("senderNumber").toString(),
                map.get("informContent").toString(),
                (Date) map.get("sentDate"),
                map.get("isRead").toString());
        return inform;
    }

    @Override
    public List<PersonalInform> getAllInform() throws SQLException {
        String sql = "SELECT * FROM t_personal_inform ";
        List<Object> list = jdbcUtil.executeQuery(sql,null);
        List<PersonalInform> informs = new ArrayList<>();
        for (Object object : list){
            Map<String,Object> map = (Map<String, Object>) object;
            PersonalInform inform = new PersonalInform(
                    (Integer) map.get("id"),
                    map.get("receiverNumber").toString(),
                    map.get("senderNumber").toString(),
                    map.get("informContent").toString(),
                    (Date) map.get("sendDate"),
                    map.get("isRead").toString());
            informs.add(inform);
        }
        return informs;
    }

    @Override
    public List<PersonalInform> getAllInformBySN(String receiverNumber) throws SQLException {
        String sql = "SELECT * FROM t_personal_inform WHERE receiverNumber = ? ";
        List<Object> list = jdbcUtil.executeQuery(sql,new Object[]{receiverNumber});
        List<PersonalInform> informs = new ArrayList<>();
        for (Object object : list){
            Map<String,Object> map = (Map<String, Object>) object;
            PersonalInform inform = new PersonalInform(
                    (Integer) map.get("id"),
                    map.get("receiverNumber").toString(),
                    map.get("senderNumber").toString(),
                    map.get("informContent").toString(),
                    (Date) map.get("sendDate"),
                    map.get("isRead").toString());
            informs.add(inform);
        }
        return informs;
    }

    @Override
    public int updateInform(PersonalInform inform) throws SQLException {
        int n;
        String sql = "UPDATE t_personal_inform SET isRead = ? WHERE id = ? AND receiverNumber = ? ";
        Object[] params = {inform.getIsRead(), inform.getId() , inform.getReceiverNumber()};
        n = jdbcUtil.executeUpdate(sql,params);
        return n;
    }

    @Override
    public int deleteOneInform(Integer id, String receiverNumber) throws SQLException {
        int n;
        String sql = "DELETE FROM t_personal_inform WHERE id = ? AND receiverNumber = ? ";
        Object[] params = {id,receiverNumber};
        n = jdbcUtil.executeUpdate(sql,params);
        return n;
    }

    @Override
    public int deleteInforms(String receiverNumber) throws SQLException {
         int n;
         String sql = "DELETE FROM t_personal_inform WHERE receiverNumber = ? ";
         n = jdbcUtil.executeUpdate(sql,new Object[]{receiverNumber});
        return n;
    }
}