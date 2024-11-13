package com.rodrigoramos.prize_draw.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "draws")
public class PrizeDraw {

    @Id
    private ObjectId id;
    private String name;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime endDate;

    public PrizeDraw() {
    }

    public PrizeDraw(ObjectId id, String name, String description, LocalDateTime creationDate, LocalDateTime endDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.endDate = endDate;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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
}
