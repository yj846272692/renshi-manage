package com.company.factory;


import com.company.service.*;
import com.company.service.impl.*;

/**
 * Service工厂
 */
public class ServiceFactory {
    public static LoginService getLoginServiceInstance(){
        return new LoginServiceImpl();
    }
    public static StaffService getStaffServiceInstance(){
        return new StaffServiceImpl();
    }
    public static CheckService getCheckServiceInstance(){
        return new CheckServiceImpl();
    }
    public static AllInformService getAllInformServiceInstance(){
        return new AllInformServiceImpl();
    }
    public static DepartmentService getDepartmentServiceInstance(){
        return new DepartmentServiceImpl();
    }
    public static SalaryService getSalaryServiceInstance(){
        return new SalaryServiceImpl();
    }
    public static PersonalInformService getPersonalInformServiceInstance(){
        return new PersonalInformServiceImpl();
    }
    public static CheckStatisticsService getCheckSSInstance(){
        return new CheckStatisticsServiceImpl();
    }
    public static LimitService getLimitServiceInstance(){
        return new LimitServiceImpl();
    }
    public static LimitItemService getLimitItemServiceInstance(){
        return new LimitItemServiceImpl();
    }
    public static LimitMapService getLimitMapServiceInstance(){
        return new LimitMapServiceImpl();
    }
    public static CountService getCountServiceInstance(){
        return new CountServiceImpl();
    }
}