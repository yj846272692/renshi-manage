package com.company.service;

import com.company.entity.Login;

import java.sql.SQLException;
import java.util.Map;

/**
 * 登录服务接口
 */
public interface LoginService {

    /**
     * 根据账号，密码得到登录的Map信息
     * @param account
     * @param password
     * @return
     */
    Map<String,Object> login(String account, String password);






    /**
     * 根据账号来删除一个登录实体，用来人事部辞退一个员工时使用
     * @param account
     * @return
     * @throws SQLException
     */
    int deleteOneLogin(String account);


    /**
     * 增加一个登录实体，用来人事部门新增员工时使用
     * @param login
     * @return
     * @throws SQLException
     */
    int addOneLogin(Login login);
}
