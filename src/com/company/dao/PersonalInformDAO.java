package com.company.dao;



import com.company.entity.PersonalInform;

import java.sql.SQLException;
import java.util.List;

/**

 * 个人私信的接口
 */
public interface PersonalInformDAO {


    /**
     * 增加一个个人私信
     * @param personalInform
     * @return
     * @throws SQLException
     */
    int addOneInform(PersonalInform personalInform) throws SQLException;


    /**
     * 根据id和receiverNumber得到一个私信
     * @param id
     * @param receiverNumber
     * @return
     * @throws SQLException
     */
    PersonalInform getOneInform(Integer id, String receiverNumber) throws SQLException;

    /**
     * 得到所有的私信
     * @return
     * @throws SQLException
     */
    List<PersonalInform> getAllInform() throws SQLException;

    /**
     * 根据接收者的工号得到私信
     * @param receiverNumber
     * @return
     * @throws SQLException
     */
    List<PersonalInform> getAllInformBySN(String receiverNumber) throws SQLException;


    /**
     * 更新个人私信
     * @param inform
     * @return
     * @throws SQLException
     */
    int updateInform(PersonalInform inform) throws SQLException;

    /**
     * 根据id，receiverNumber删除选中的私信
     * @param id
     * @param receiverNumber
     * @return
     * @throws SQLException
     */
    int deleteOneInform(Integer id, String receiverNumber)throws SQLException;


    /**
     * 根据工号删除与他有关的所有私信
     * @param receiverNumber
     * @return
     * @throws SQLException
     */
    int deleteInforms(String receiverNumber) throws SQLException;
}
