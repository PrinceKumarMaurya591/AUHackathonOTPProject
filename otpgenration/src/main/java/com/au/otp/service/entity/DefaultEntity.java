package com.au.otp.service.entity;


import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Audited
@Component
public class DefaultEntity {

    @Column(name = "action", length = 20, nullable = false)
    private String action;

    @CreatedBy
    @Column(name = "created_by", length = 15, nullable = false)
    private String createdBy;

    @NotAudited
    @Column(name = "created_time", nullable = false)
    private LocalDateTime creationDateTime;

    @NotAudited
    @Column(name = "updated_by", length = 15, nullable = true)
    private String updatedBy;

    @NotAudited
    @Column(name = "updated_time", nullable = true)
    private LocalDateTime updatedTime;

    // Getters and Setters

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }
}
