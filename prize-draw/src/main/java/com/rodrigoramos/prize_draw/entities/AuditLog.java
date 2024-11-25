package com.rodrigoramos.prize_draw.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "logs")
public class AuditLog {

    @Id
    private String id;
    private LocalDateTime date = LocalDateTime.now();
    private String action;
    private String details;
    private String prizeDrawId;

    public AuditLog() {
    }

    public AuditLog(String id, LocalDateTime date, String action, String details, String prizeDrawId) {
        this.id = id;
        this.date = date;
        this.action = action;
        this.details = details;
        this.prizeDrawId = prizeDrawId;
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
