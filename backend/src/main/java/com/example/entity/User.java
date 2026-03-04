package com.example.entity;

import java.io.Serial;
import java.util.Date;
import java.io.Serializable;

/**
 * 用户信息表(User)实体类
 *
 * @author makejava
 * @since 2026-03-03 22:27:31
 */
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 292508883616104415L;
/**
     * 用户唯一ID
     */
    private Integer id;
/**
     * 用户名
     */
    private String username;
/**
     * 年龄
     */
    private Integer age;
/**
     * 创建时间
     */
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

