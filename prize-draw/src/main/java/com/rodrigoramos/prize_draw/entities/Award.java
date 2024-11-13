package com.rodrigoramos.prize_draw.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "awards")
public class Award {

    @Id
    private String id;
    private String name;
    private PrizeDraw prizeDraw;

    public Award() {
    }

    public Award(String id, String name, PrizeDraw prizeDraw) {
        this.id = id;
        this.name = name;
        this.prizeDraw = prizeDraw;
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

    public PrizeDraw getPrizeDraw() {
        return prizeDraw;
    }

    public void setPrizeDraw(PrizeDraw prizeDraw) {
        this.prizeDraw = prizeDraw;
    }
}
