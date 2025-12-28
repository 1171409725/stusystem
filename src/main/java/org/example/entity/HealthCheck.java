package org.example.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 卫生检查记录实体类
 */
public class HealthCheck {
    private Integer id;              // 检查记录ID
    private Integer dormitoryId;     // 宿舍ID
    private String checkDate;        // 检查日期
    private BigDecimal score;        // 评分（0-100）
    private String grade;            // 等级（优秀/良好/合格/不合格）
    private String comment;          // 检查意见
    private Integer checkerId;       // 检查人员ID
    private String checkerName;      // 检查人员姓名（冗余字段）
    private Date createTime;         // 创建时间
    private Date updateTime;         // 更新时间

    // 关联对象
    private Dormitory dormitory;     // 宿舍信息
    private User checker;            // 检查人员信息

    public HealthCheck() {
    }

    public HealthCheck(Integer id, Integer dormitoryId, String checkDate, BigDecimal score, 
                       String grade, String comment, Integer checkerId, String checkerName, 
                       Date createTime, Date updateTime) {
        this.id = id;
        this.dormitoryId = dormitoryId;
        this.checkDate = checkDate;
        this.score = score;
        this.grade = grade;
        this.comment = comment;
        this.checkerId = checkerId;
        this.checkerName = checkerName;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Integer dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(Integer checkerId) {
        this.checkerId = checkerId;
    }

    public String getCheckerName() {
        return checkerName;
    }

    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }

    public User getChecker() {
        return checker;
    }

    public void setChecker(User checker) {
        this.checker = checker;
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

    public Dormitory getDormitory() {
        return dormitory;
    }

    public void setDormitory(Dormitory dormitory) {
        this.dormitory = dormitory;
    }

    @Override
    public String toString() {
        return "HealthCheck{" +
                "id=" + id +
                ", dormitoryId=" + dormitoryId +
                ", checkDate='" + checkDate + '\'' +
                ", score=" + score +
                ", grade='" + grade + '\'' +
                ", comment='" + comment + '\'' +
                ", checkerId=" + checkerId +
                ", checkerName='" + checkerName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

