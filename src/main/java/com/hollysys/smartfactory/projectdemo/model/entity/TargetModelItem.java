package com.hollysys.smartfactory.projectdemo.model.entity;

import java.io.Serializable;

/**
 * (PowerIndexTargetModelItem)实体类
 * @author lizhi186545
 */
public class TargetModelItem implements Serializable {

    private static final long serialVersionUID = 7465350001901004809L;

    public TargetModelItem() {
    }

    public TargetModelItem(String id, String groupId, String itemId) {
        this.id = id;
        this.groupId = groupId;
        this.itemId = itemId;
    }

    private String id;

    private String groupId;

    private String itemId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "TargetModelItem{" +
                "id='" + id + '\'' +
                ", groupId='" + groupId + '\'' +
                ", itemId='" + itemId + '\'' +
                '}';
    }
}
