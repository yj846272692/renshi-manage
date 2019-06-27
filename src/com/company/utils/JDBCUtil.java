package com.company.utils;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JDBC辅助类，用于构建数据库连接（采用单例模式），封装了增删改查的基本功能，方便具体的DAO类的实现
 */
public final class JDBCUtil {
    private static String url = "jdbc:mysql://127.0.0.1:3306/db_company?useUnicode=true&characterEncoding=utf8";

//    private static String url = "jdbc:mysql://localhost:3306/db_company";
    private static String name = "root";
    private static String password = "root";
    private static Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static JDBCUtil jdbcUtil = null;

    public static JDBCUtil getInitJDBCUtil(){
        if (jdbcUtil == null){
            //线程加锁
            synchronized (JDBCUtil.class){
                if (jdbcUtil == null){
                    //懒汉式加载
                    jdbcUtil = new JDBCUtil();
                }
            }
        }
        return jdbcUtil;
    }

    private JDBCUtil(){
    }

    //通过静态代码块注册数据库驱动，保证注册只执行一次
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    //获得连接
    public Connection getConnection(){
        try {
            connection = DriverManager.getConnection(url,name,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    /**
     * insert update delete SQL语句的统一执行方法
     * @param sql  SQL语句
     * @param params  参数数组，如没有则为null
     * @return  返回受影响的行数
     */
    public int executeUpdate(String sql,Object[] params){
        //受影响的行数
        int affectedLine = 0;
        try {
            //获得连接
            connection = this.getConnection();
            //调用SQL语句
            preparedStatement = connection.prepareStatement(sql);
            //参数赋值
            if (params != null){
                for (int i=0;i<params.length;i++){
                    preparedStatement.setObject(i+1,params[i]);
                }
            }
            //执行更新操作
            affectedLine = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            //释放所有资源
            closeAll();
        }
        return affectedLine;
    }


    /**
     * SQL 查询将查询结果直接放进ResultSet中
     * @param sql  sql语句
     * @param params  参数数组，若没有则为null
     * @return 返回结果集
     */
    private ResultSet executeQueryRS(String sql,Object[] params){
        try{
            connection = this.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (params != null){
                for (int i=0;i<params.length;i++){
                    preparedStatement.setObject(i+1,params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return resultSet;
    }


    /**
     * 获取结果集，并将结果集放在list中
     * @param sql sql语句
     * @param params 参数数组
     * @return List结果集
     */
    public List<Object> executeQuery(String sql,Object[] params){
        //执行SQL获得结果集
        ResultSet rs = executeQueryRS(sql,params);
        //ResultSetMetaData接口，用于获取关于ResultSet对象中列的类型和属性信息的对象
        ResultSetMetaData rsmd = null;
        //结果集列数
        int columnCount = 0;
        try {
            //获得结果集的元数据信息
            rsmd = rs.getMetaData();
            //获得结果集列数
            columnCount = rsmd.getColumnCount();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //创建list
        List<Object> list = new ArrayList<Object>();
        try {
            //以下代码把ResultSet的结果放到List中
            //循环遍历所有的记录
            while (rs.next()){
                Map<String ,Object> map = new HashMap<String ,Object>();
                //循环遍历一条记录的所有列
                for (int i=1;i<=columnCount;i++){
                    //将列名，对应的列的值放到map的对象中
                    map.put(rsmd.getColumnLabel(i),rs.getObject(i));
                }
                //将一条记录放进list中
                list.add(map);
            }
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }finally {
            //释放所有资源
            closeAll();
        }
        return list;
    }


    /**
     *  sql 查询将查询结果集：一行
     * @param sql  sql 语句
     * @param params  参数数组，若没有则为null
     * @return  结果集
     */
    public Map<String , Object> executeQuerySingle(String sql, Object[] params){
        Map<String , Object> map = new HashMap<String , Object>();
        try {
            connection = this.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (params != null){
                for (int i=0;i<params.length;i++){
                    preparedStatement.setObject(i+1,params[i]);
                }
            }
            resultSet = executeQueryRS(sql,params);
            ResultSetMetaData rsmd = null;
            int columnCount = 0;

            try {
                rsmd = resultSet.getMetaData();
                columnCount = rsmd.getColumnCount();
            }catch (SQLException e){
                System.err.println(e.getMessage());
            }
            if (resultSet.next()){
                for (int i=1;i<=columnCount;i++){
                    map.put(rsmd.getColumnLabel(i),resultSet.getObject(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }

        return map;
    }

    //关闭所有资源
    public void closeAll(){
        //关闭结果集对象
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        //关闭preparedStatement对象
        if (preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        //关闭Connection对象
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
