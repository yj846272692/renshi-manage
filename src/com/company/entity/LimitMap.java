package com.company.entity;

/**

 * 权限映射实体类
 */
public class LimitMap {
    private int id;
    private String staffNumber;
    private String itemID;

    public LimitMap() {

    }

    public LimitMap( String staffNumber, String itemID) {
        this.staffNumber = staffNumber;
        this.itemID = itemID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    @Override
    public String toString() {
        return "LimitMap{" +
                "id=" + id +
                ", staffNumber='" + staffNumber + '\'' +
                ", itemID='" + itemID + '\'' +
                '}';
    }
}