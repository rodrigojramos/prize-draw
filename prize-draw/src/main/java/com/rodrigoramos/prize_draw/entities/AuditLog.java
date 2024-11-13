package com.rodrigoramos.prize_draw.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "logs")
public class AuditLog {

    @Id
    private ObjectId id;
    private LocalDateTime date;
    private String action;
    private String details;

    public AuditLog() {
    }

    public AuditLog(ObjectId id, LocalDateTime date, String action, String details) {
        this.id = id;
        this.date = date;
        this.action = action;
        this.details = details;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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
}
