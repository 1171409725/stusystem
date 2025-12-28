package org.example.entity;

import java.util.Date;

/**
 * 权限申请实体类
 */
public class PermissionApplication {
    private Integer id;              // 申请ID
    private Integer userId;          // 申请人ID
    private String applicantName;    // 申请人姓名
    private String applicationType;  // 申请类型（inspector）
    private String reason;           // 申请理由
    private String status;           // 状态（pending/approved/rejected）
    private Integer reviewerId;      // 审核人ID
    private String reviewerName;     // 审核人姓名
    private String reviewComment;    // 审核意见
    private Date applyTime;          // 申请时间
    private Date reviewTime;         // 审核时间

    // 关联对象
    private User applicant;          // 申请人信息

    public PermissionApplication() {
    }

    public PermissionApplication(Integer id, Integer userId, String applicantName, String applicationType,
                                String reason, String status, Integer reviewerId, String reviewerName,
                                String reviewComment, Date applyTime, Date reviewTime) {
        this.id = id;
        this.userId = userId;
        this.applicantName = applicantName;
        this.applicationType = applicationType;
        this.reason = reason;
        this.status = status;
        this.reviewerId = reviewerId;
        this.reviewerName = reviewerName;
        this.reviewComment = reviewComment;
        this.applyTime = applyTime;
        this.reviewTime = reviewTime;
    }

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

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Integer reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    @Override
    public String toString() {
        return "PermissionApplication{" +
                "id=" + id +
                ", userId=" + userId +
                ", applicantName='" + applicantName + '\'' +
                ", applicationType='" + applicationType + '\'' +
                ", status='" + status + '\'' +
                ", reviewerName='" + reviewerName + '\'' +
                '}';
    }
}

