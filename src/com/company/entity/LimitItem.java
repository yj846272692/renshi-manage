package com.company.entity;

/**

 * 权限项实体类
 */
public class LimitItem {
    private String itemID;
    private String groupID;
    private String itemName;

    public LimitItem() {

    }

    public LimitItem(String itemID, String groupID, String itemName) {
        this.itemID = itemID;
        this.groupID = groupID;
        this.itemName = itemName;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "LimitItemDAO{" +
                "itemID='" + itemID + '\'' +
                ", groupID='" + groupID + '\'' +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}