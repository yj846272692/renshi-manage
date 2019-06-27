package com.company.entity;

/**

 * 按考勤类型来全部统计人数
 */
public class TypeCheckCount {
    private String checkType;
    private Integer count;

    public TypeCheckCount(String checkType, Integer count) {
        this.checkType = checkType;
        this.count = count;
    }

    public TypeCheckCount(){

    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "TypeCheckCount{" +
                "typeName='" + checkType + '\'' +
                ", count=" + count +
                '}';
    }
}
