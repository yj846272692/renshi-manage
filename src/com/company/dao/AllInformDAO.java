package com.company.dao;



import com.company.entity.AllInform;

import java.sql.SQLException;
import java.util.List;

/**
 * 全体通知的接口
 */
public interface AllInformDAO {

    /**
     * 得到所有的全体通知
     * @return
     * @throws SQLException
     */
    List<AllInform> getAllInform() throws SQLException;

    /**
     * 增加一个全体通知
     * @param inform
     * @return
     * @throws SQLException
     */
    int addOneInform(AllInform inform) throws SQLException;
}
