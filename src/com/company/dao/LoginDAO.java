package com.company.dao;





import com.company.entity.Login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * 登录实体的接口
 */
public interface LoginDAO {

    /**
     * 增加一个登录实体，用来人事部门新增员工时使用
     * @param login
     * @return
     * @throws SQLException
     */
    int addOneLogin(Login login) throws SQLException;


    /**
     * 根据账号来删除一个登录实体，用来人事部辞退一个员工时使用
     * @param account
     * @return
     * @throws SQLException
     */
    int deleteOneLogin(String account) throws SQLException;


    /**
     * 更新一个登录实体，用来修改密码时使用
     * @param login
     * @return
     * @throws SQLException
     */
    int updateLoginInform(Login login) throws SQLException;

    /**
     * 根据账号来查找一个登录实体，用来登录时判断
     * @param account
     * @return
     * @throws SQLException
     */
    Login getOneLogin(String account) throws SQLException;

    /**
     * 从文本文件实现登陆
     * @param fileName
     * @return
     * @throws IOException
     */
    Map<String, String> getLogin(String fileName) throws IOException;
}
