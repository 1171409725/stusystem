package org.example.entity;

import java.util.Date;

/**
 * 宿舍实体类
 */
public class Dormitory {
    private Integer id;          // 宿舍ID
    private String building;     // 楼栋号
    private String roomNumber;   // 房间号
    private Integer capacity;    // 容纳人数
    private Integer currentCount;// 当前人数
    private String status;       // 状态（正常/维修中等）
    private Date createTime;     // 创建时间
    private Date updateTime;     // 更新时间

    public Dormitory() {
    }

    public Dormitory(Integer id, String building, String roomNumber, Integer capacity, 
                     Integer currentCount, String status, Date createTime, Date updateTime) {
        this.id = id;
        this.building = building;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.currentCount = currentCount;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(Integer currentCount) {
        this.currentCount = currentCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "Dormitory{" +
                "id=" + id +
                ", building='" + building + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", capacity=" + capacity +
                ", currentCount=" + currentCount +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

