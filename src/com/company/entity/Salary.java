package com.company.entity;

import java.sql.Date;

/**

 * 工资实体类
 */
public class Salary {
    private Integer id;//流水号
    private String staffNumber;//员工工号
    private Double basicSalary;//基础工资
    private Double bfSalary;//补发工资
    private Double deductSalary;//应扣工资
    private Double personalTax;//税收
    private Double socialSec;//社保
    private Double reservedFunds;//公积金
    private Double finalSalary;//实发工资
    private Date time;//发放时间

    public Salary( String staffNumber, Double basicSalary, Double bfSalary,
                   Double deductSalary, Double personalTax, Double socialSec,
                   Double reservedFunds, Double finalSalary, Date time) {
        this.staffNumber = staffNumber;
        this.basicSalary = basicSalary;
        this.bfSalary = bfSalary;
        this.deductSalary = deductSalary;
        this.personalTax = personalTax;
        this.socialSec = socialSec;
        this.reservedFunds = reservedFunds;
        this.finalSalary = finalSalary;
        this.time = time;
    }
    public Salary(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }

    public Double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Double getBfSalary() {
        return bfSalary;
    }

    public void setBfSalary(Double bfSalary) {
        this.bfSalary = bfSalary;
    }

    public Double getDeductSalary() {
        return deductSalary;
    }

    public void setDeductSalary(Double deductSalary) {
        this.deductSalary = deductSalary;
    }

    public Double getPersonalTax() {
        return personalTax;
    }

    public void setPersonalTax(Double personalTax) {
        this.personalTax = personalTax;
    }

    public Double getSocialSec() {
        return socialSec;
    }

    public void setSocialSec(Double socialSec) {
        this.socialSec = socialSec;
    }

    public Double getReservedFunds() {
        return reservedFunds;
    }

    public void setReservedFunds(Double reservedFunds) {
        this.reservedFunds = reservedFunds;
    }

    public Double getFinalSalary() {
        return finalSalary;
    }

    public void setFinalSalary(Double finalSalary) {
        this.finalSalary = finalSalary;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", staffNumber='" + staffNumber + '\'' +
                ", basicSalary=" + basicSalary +
                ", bfSalary=" + bfSalary +
                ", deductSalary=" + deductSalary +
                ", personalTax=" + personalTax +
                ", socialSec=" + socialSec +
                ", reservedFunds=" + reservedFunds +
                ", finalSalary=" + finalSalary +
                ", time=" + time +
                '}';
    }
}
