package com.rodrigoramos.prize_draw.dto;

import com.rodrigoramos.prize_draw.entities.Award;
import com.rodrigoramos.prize_draw.entities.PrizeDraw;

public class AwardDto {

    private String id;
    private String name;
    private PrizeDraw prizeDraw;

    public AwardDto() {
    }

    public AwardDto(String id, String name, PrizeDraw prizeDraw) {
        this.id = id;
        this.name = name;
        this.prizeDraw = prizeDraw;
    }

    public AwardDto(Award entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.prizeDraw = entity.getPrizeDraw();
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
