package com.company.service.impl;



import com.company.dao.LimitDAO;
import com.company.dao.LoginDAO;
import com.company.dao.StaffDAO;
import com.company.factory.DAOFactory;
import com.company.entity.Login;
import com.company.entity.Staff;
import com.company.service.LoginService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录服务实现类
 */
public class LoginServiceImpl implements LoginService {
    private LoginDAO loginDAO = DAOFactory.getLoginDAOInstance();
    private StaffDAO staffDAO = DAOFactory.getStaffDAOInstance();
    private LimitDAO limitDAO = DAOFactory.getLimitDAOInstance();
    @Override
    public Map<String, Object> login(String account, String password) {
        Map<String,Object> map = new HashMap<>();
        List<String> groupNames = null;
        Map<String , List<String>> itemMap = null;
        Login login = null;
        Staff staff = null;
        try {
            login = loginDAO.getOneLogin(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (login != null){
            if (login.getPassword().equals(password)){
                try {
                   groupNames = limitDAO.getGroupName(login.getAccount());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    itemMap = limitDAO.getItemName(groupNames,login.getAccount());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    staff = staffDAO.getOneStaff(login.getAccount());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                map.put("info","登录成功");
                map.put("staff",staff);
                map.put("groupNames",groupNames);
                map.put("itemMap",itemMap);
            }else {
                map.put("info","密码不对");
            }
        }else {
            map.put("info","账号不存在");
        }
        return map;
    }

    @Override
    public int deleteOneLogin(String account) {
        int n =0;
        try {
            n = loginDAO.deleteOneLogin(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public int addOneLogin(Login login) {
        int n=0;
        try {
            n = loginDAO.addOneLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
}