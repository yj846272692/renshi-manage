package com.company.entity;

/**

 * 权限组实体类
 */
public class LimitGroup {
    private String groupID;
    private String groupName;

    public LimitGroup() {

    }

    public LimitGroup(String groupID, String groupName) {
        this.groupID = groupID;
        this.groupName = groupName;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "LimitGroup{" +
                "groupID='" + groupID + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}