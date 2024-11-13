package com.rodrigoramos.prize_draw.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "participants")
public class Participant {

    @Id
    private String id;
    private String name;
    private String document;
    private String email;
    private List<String> prizeDrawsId = new ArrayList<>();

    public Participant() {
    }

    public Participant(String id, String name, String document, String email) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.email = email;
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

    public List<String> getPrizeDrawsId() {
        return prizeDrawsId;
    }
}
