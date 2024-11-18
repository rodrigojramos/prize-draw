package com.rodrigoramos.prize_draw.dto;

import com.rodrigoramos.prize_draw.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserDto {

    private String id;
    private String name;
    private String document;
    private String email;
    private Integer quantityPrizeDraw = 0;
    private List<String> prizeDrawsId = new ArrayList<>();

    public UserDto() {
    }

    public UserDto(String id, String name, String document, String email, Integer quantityPrizeDraw, List<String> prizeDrawsId) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.email = email;
        this.quantityPrizeDraw = quantityPrizeDraw;
        this.prizeDrawsId = prizeDrawsId;
    }

    public UserDto(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.document = entity.getDocument();
        this.email = entity.getEmail();
        this.quantityPrizeDraw = entity.getQuantityPrizeDraw();
        this.prizeDrawsId = entity.getDraws();
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

    public List<String> getPrizeDrawsId() {
        return prizeDrawsId;
    }

    public void setPrizeDrawsId(List<String> prizeDrawsId) {
        this.prizeDrawsId = prizeDrawsId;
    }
}
