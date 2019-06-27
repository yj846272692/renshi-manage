package com.company.entity;

/**

 * 用于制作个人考勤统计图的model类
 */
public class PersonalCheckCount {
    private String checkType;
    private Integer checkCount;

    public PersonalCheckCount() {

    }

    public PersonalCheckCount(String checkType, Integer checkCount) {
        this.checkType = checkType;
        this.checkCount = checkCount;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public Integer getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(Integer checkCount) {
        this.checkCount = checkCount;
    }

    @Override
    public String toString() {
        return "PersonalCheckCount{" +
                "checkType='" + checkType + '\'' +
                ", checkCount=" + checkCount +
                '}';
    }
}