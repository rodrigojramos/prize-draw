package com.rodrigoramos.prize_draw.dto;

import com.rodrigoramos.prize_draw.entities.User;
import org.bson.types.ObjectId;

public class UserDto {

    private String id;
    private String name;
    private String document;
    private String email;
    private Integer quantityPrizeDraw;

    public UserDto() {
    }

    public UserDto(String id, String name, String document, String email, Integer quantityPrizeDraw) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.email = email;
        this.quantityPrizeDraw = quantityPrizeDraw;
    }

    public UserDto(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.document = entity.getDocument();
        this.email = entity.getEmail();
        this.quantityPrizeDraw = entity.getQuantityPrizeDraw();
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

    public Integer getQuantityPrizeDraw() {
        return quantityPrizeDraw;
    }

    public void setQuantityPrizeDraw(Integer quantityPrizeDraw) {
        this.quantityPrizeDraw = quantityPrizeDraw;
    }
}
