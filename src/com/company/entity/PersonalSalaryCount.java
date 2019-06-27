package com.company.entity;

/**
 * 个人工资饼图的model类
 */
public class PersonalSalaryCount {
    private String salaryType;
    private Double salaryCount;

    public PersonalSalaryCount() {
    }

    public PersonalSalaryCount(String salaryType, Double salaryCount) {
        this.salaryType = salaryType;
        this.salaryCount = salaryCount;
    }


    public String getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(String salaryType) {
        this.salaryType = salaryType;
    }

    public Double getSalaryCount() {
        return salaryCount;
    }

    public void setSalaryCount(Double salaryCount) {
        this.salaryCount = salaryCount;
    }

    @Override
    public String toString() {
        return "PersonalSalaryCount{" +
                "salaryType='" + salaryType + '\'' +
                ", salaryCount=" + salaryCount +
                '}';
    }
}
