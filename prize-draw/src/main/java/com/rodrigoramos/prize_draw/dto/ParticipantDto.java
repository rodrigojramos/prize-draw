package com.rodrigoramos.prize_draw.dto;

import com.rodrigoramos.prize_draw.entities.Participant;

public class ParticipantDto {

    private String id;
    private String name;
    private String document;
    private String email;

    public ParticipantDto() {
    }

    public ParticipantDto(String id, String name, String document, String email) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.email = email;
    }

    public ParticipantDto(Participant entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.document = entity.getDocument();
        this.email = entity.getEmail();
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

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
