package com.company.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 权限服务的接口
 */
public interface LimitService {
    /**
     * 根据工号得到员工所拥有的权限组的名字
     * @param staffNumber
     * @return
     * @throws SQLException
     */
    List<String> getGroupName(String staffNumber);


    /**
     * 根据groupName和staffNumber得到权限组与权限项的Map集合
     * @param groupName
     * @param staffNumber
     * @return
     * @throws SQLException
     */
    Map<String,List<String>> getItemName(List<String> groupName, String staffNumber);


    /**
     * 得到含有权限项名和编号的Map
     * @return
     * @throws SQLException
     */
    Map<String,String> getItemIDAndName(String staffNumber);
}
