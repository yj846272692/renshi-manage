package com.company.entity;

/**

 * 每次发放工资时的最终工资统计
 */
public class EveryTimeSalaryCount {
    String everyTime;
    Double salaryCount;

    public EveryTimeSalaryCount() {

    }

    public EveryTimeSalaryCount(String everyTime, Double salaryCount) {
        this.everyTime = everyTime;
        this.salaryCount = salaryCount;
    }

    public String getEveryTime() {
        return everyTime;
    }

    public void setEveryTime(String everyTime) {
        this.everyTime = everyTime;
    }

    public Double getSalaryCount() {
        return salaryCount;
    }

    public void setSalaryCount(Double salaryCount) {
        this.salaryCount = salaryCount;
    }


    @Override
    public String toString() {
        return "EveryTimeSalaryCount{" +
                "everyTime='" + everyTime + '\'' +
                ", salaryCount=" + salaryCount +
                '}';
    }
}