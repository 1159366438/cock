package com.example.entity;

import java.io.Serial;
import java.util.Date;
import java.io.Serializable;

/**
 * 打卡记录表(AttendanceRecord)实体类
 *
 * @author makejava
 * @since 2026-03-03 22:27:11
 */
public class AttendanceRecord implements Serializable {
    @Serial
    private static final long serialVersionUID = -55051411624066171L;
/**
     * 主键ID
     */
    private Integer id;
/**
     * 打卡用户ID
     */
    private Integer userId;
/**
     * 打卡时间
     */
    private Date checkInTime;
/**
     * 打卡类型：1-上班打卡 2-下班打卡 3-加班打卡
     */
    private Integer checkInType;
/**
     * 打卡状态：1-正常 2-迟到 3-早退 4-旷工
     */
    private Integer checkInStatus;
/**
     * 打卡地点（如地址/经纬度）
     */
    private String checkInLocation;
/**
     * 记录创建时间
     */
    private Date createTime;
/**
     * 记录更新时间
     */
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Integer getCheckInType() {
        return checkInType;
    }

    public void setCheckInType(Integer checkInType) {
        this.checkInType = checkInType;
    }

    public Integer getCheckInStatus() {
        return checkInStatus;
    }

    public void setCheckInStatus(Integer checkInStatus) {
        this.checkInStatus = checkInStatus;
    }

    public String getCheckInLocation() {
        return checkInLocation;
    }

    public void setCheckInLocation(String checkInLocation) {
        this.checkInLocation = checkInLocation;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}