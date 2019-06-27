package com.company.service.impl;



import com.company.dao.AllInformDAO;
import com.company.factory.DAOFactory;
import com.company.entity.AllInform;
import com.company.service.AllInformService;

import java.sql.SQLException;
import java.util.List;

/**

 * 全体通知服务实现类
 */
public class AllInformServiceImpl implements AllInformService {
    private AllInformDAO allInformDAO = DAOFactory.getAllInformDAOInstance();
    @Override
    public List<AllInform> getAllInform() {
        List<AllInform> allInforms = null;
        try {
            allInforms = allInformDAO.getAllInform();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (allInforms.size() != 0){
            return allInforms;
        }else {
            return null;
        }
    }

    @Override
    public int addOneInform(AllInform inform) {
        int n =0;
        try {
            n = allInformDAO.addOneInform(inform);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
}