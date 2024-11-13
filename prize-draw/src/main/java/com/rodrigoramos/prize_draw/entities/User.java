package com.rodrigoramos.prize_draw.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
public class User {

    @Id
    private ObjectId id;
    private String name;
    private String document;
    private String email;
    private Integer quantityPrizeDraw;
    private List<PrizeDraw> draws = new ArrayList<>();

    public User() {
    }

    public User(ObjectId id, String name, String document, String email, Integer quantityPrizeDraw) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.email = email;
        this.quantityPrizeDraw = quantityPrizeDraw;
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

    public Integer getQuantityPrizeDraw() {
        return quantityPrizeDraw;
    }

    public void setQuantityPrizeDraw(Integer quantityPrizeDraw) {
        this.quantityPrizeDraw = quantityPrizeDraw;
    }

    public List<PrizeDraw> getDraws() {
        return draws;
    }
}