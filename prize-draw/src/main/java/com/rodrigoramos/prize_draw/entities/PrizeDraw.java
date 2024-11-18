package com.rodrigoramos.prize_draw.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "draws")
public class PrizeDraw {

    @Id
    private String id;
    private String name;
    private String description;
    private LocalDateTime creationDate = LocalDateTime.now();
    private LocalDateTime endDate;
    private User creator;
    private List<String> awards = new ArrayList<>();
    private List<String> participantsId = new ArrayList<>();
    private List<String> auditLogsId = new ArrayList<>();

    public PrizeDraw() {
    }

    public PrizeDraw(String id, String name, String description, LocalDateTime endDate, User creator) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = LocalDateTime.now();
        this.endDate = endDate;
        this.creator = creator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<String> getAwards() {
        return awards;
    }

    public void setAwards(List<String> awards) {
        this.awards = awards;
    }

    public List<String> getParticipantsId() {
        return participantsId;
    }

    public List<String> getAuditLogsId() {
        return auditLogsId;
    }
}
