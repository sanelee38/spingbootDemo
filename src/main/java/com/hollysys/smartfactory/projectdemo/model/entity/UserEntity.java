package com.hollysys.smartfactory.projectdemo.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

public class UserEntity implements Serializable {
    private static final long serialVersionUID = -1245647698788396535L;
    private Long id;
    private String guid;
    private String name;
    private String age;
    private Date createTime;

    public UserEntity() {
    }

    public UserEntity(Long id, String guid, String name, String age, Date createTime) {
        this.id = id;
        this.guid = guid;
        this.name = name;
        this.age = age;
        this.createTime = createTime;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", guid='" + guid + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
