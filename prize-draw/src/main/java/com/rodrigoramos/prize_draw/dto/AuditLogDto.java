package com.rodrigoramos.prize_draw.dto;

import com.rodrigoramos.prize_draw.entities.AuditLog;

import java.time.LocalDateTime;

public class AuditLogDto {

    private String id;
    private LocalDateTime date;
    private String action;
    private String details;
    private String prizeDrawId;

    public AuditLogDto() {
    }

    public AuditLogDto(String id, LocalDateTime date, String action, String details, String prizeDrawId) {
        this.id = id;
        this.date = date;
        this.action = action;
        this.details = details;
        this.prizeDrawId = prizeDrawId;
    }

    public AuditLogDto(AuditLog entity) {
        this.id = entity.getId();
        this.date = entity.getDate();
        this.action = entity.getAction();
        this.details = entity.getDetails();
        this.prizeDrawId = entity.getPrizeDrawId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPrizeDrawId() {
        return prizeDrawId;
    }

    public void setPrizeDrawId(String prizeDrawId) {
        this.prizeDrawId = prizeDrawId;
    }
}
