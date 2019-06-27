package com.company.entity;

/**

 * 考勤统计实体类
 */
public class CheckStatistics {
    private Integer id;//流水号
    private String staffNumber;//员工工号
    private Integer normalCount;//
    private Integer truantCount;
    private Integer lateCount;
    private Integer leaveCount;
    private Integer holidayCount;

    public CheckStatistics( String staffNumber, Integer normalCount, Integer truantCount,
                           Integer lateCount, Integer leaveCount, Integer holidayCount) {
        this.staffNumber = staffNumber;
        this.normalCount = normalCount;
        this.truantCount = truantCount;
        this.lateCount = lateCount;
        this.leaveCount = leaveCount;
        this.holidayCount = holidayCount;
    }

    public CheckStatistics(){

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

    public Integer getNormalCount() {
        return normalCount;
    }

    public void setNormalCount(Integer normalCount) {
        this.normalCount = normalCount;
    }

    public Integer getTruantCount() {
        return truantCount;
    }

    public void setTruantCount(Integer truantCount) {
        this.truantCount = truantCount;
    }

    public Integer getLateCount() {
        return lateCount;
    }

    public void setLateCount(Integer lateCount) {
        this.lateCount = lateCount;
    }

    public Integer getLeaveCount() {
        return leaveCount;
    }

    public void setLeaveCount(Integer leaveCount) {
        this.leaveCount = leaveCount;
    }

    public Integer getHolidayCount() {
        return holidayCount;
    }

    public void setHolidayCount(Integer holidayCount) {
        this.holidayCount = holidayCount;
    }

    @Override
    public String toString() {
        return "CheckStatistics{" +
                "id=" + id +
                ", staffNumber='" + staffNumber + '\'' +
                ", normalCount=" + normalCount +
                ", truantCount=" + truantCount +
                ", lateCount=" + lateCount +
                ", leaveCount=" + leaveCount +
                ", holidayCount=" + holidayCount +
                '}';
    }
}
