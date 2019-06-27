package com.company.dao.impl;





import com.company.dao.LoginDAO;
import com.company.entity.Login;
import com.company.utils.JDBCUtil;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**

 * 登录DAO的实现类
 */
public class LoginDAOImpl implements LoginDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
    @Override
    public int addOneLogin(Login login) throws SQLException {
        int n;
        String sql = "INSERT INTO t_login VALUES(?,?) ";
        Object[] params = {login.getAccount(),login.getPassword()};
        n = jdbcUtil.executeUpdate(sql,params);
        return n;
    }

    @Override
    public int deleteOneLogin(String account) throws SQLException {
        int n;
        String sql = "DELETE FROM t_login WHERE account = ? ";
        n = jdbcUtil.executeUpdate(sql,new Object[]{account});
        return n;
    }

    @Override
    public int updateLoginInform(Login login) throws SQLException {
        int n;
        String sql = "UPDATE t_login SET password = ? WHERE account = ? ";
        Object[] params = {login.getPassword(),login.getAccount()};
        n = jdbcUtil.executeUpdate(sql,params);
        return n;
    }

    @Override
    public Login getOneLogin(String account) throws SQLException {
//        String sql = "SELECT * FROM t_login WHERE account = ? ";
////        Map<String,Object> map = jdbcUtil.executeQuerySingle(sql,new Object[]{account});
////        if (map.size() !=0 ){
////            Login login = new Login(map.get("account").toString(),map.get("password").toString());
////            return login;
////        }else {
////            return null;
////        }
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_login WHERE account = ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, account);
        ResultSet rs = pstmt.executeQuery();
        Login login = null;
        while (rs.next()) {
            String account1 = rs.getString("account");
            String password = rs.getString("password");
            login = new Login();
            login.setAccount(account1);
            login.setPassword(password);
        }
        rs.close();
        pstmt.close();
        return login;
    }

    @Override
    public Map<String, String> getLogin(String fileName) throws IOException {

        String account = "111111";
        String password = "111111";
        Map<String, String> map = new HashMap<String , String>();
        String path = "D:\\1.txt";
        File file = new File(path);
        System.out.println(file);
        BufferedReader bReader = new BufferedReader(new FileReader(file));
        System.out.println(bReader);
        String string =  "";
        String pc [] = null;
        String key = "";
        String value = "";
        @SuppressWarnings("unused")
        boolean result = false;
        while ((string = bReader.readLine()) != null) {
            pc = string.split(",");
            key = pc[0];
            value = pc[1];
            map.put(key, value);
        }
        Set<String> keySet = map.keySet();
        for (String string2 : keySet) {
            System.out.println(string2);//账号
            System.out.println(map.get(string2));//密码
            if (account .equals(string2) && password.equals(map.get(string2))) {
                result = true;
                break;
            }
        }
        if (result = false) {
            System.out.println("密码错误");
        }else {
            System.out.println("账号密码正确");
        }
        bReader.close();
        System.out.println(map);
        return map;
    }


}