package com.company.dao;




import com.company.entity.LimitGroup;

import java.sql.SQLException;
import java.util.List;

/**

 * 权限组接口
 */
public interface LimitGroupDAO {

    /**
     * 根据groupID得到一个权限组
     * @param groupID
     * @return
     * @throws SQLException
     */
    LimitGroup getLimitGroup(String groupID) throws SQLException;


    /**
     * 得到所有的权限组信息
     * @return
     * @throws SQLException
     */
    List<LimitGroup> getAllLimitGroup() throws SQLException;

}
