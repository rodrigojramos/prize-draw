package com.rodrigoramos.prize_draw.dto;

import com.rodrigoramos.prize_draw.entities.PrizeDraw;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class PrizeDrawDto {

    private String id;
    private String name;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime endDate;
    private UserDto creator;
    private List<String> awards;
    private Set<String> participantsId;
    private List<String> auditLogsId;
    private List<ParticipantDto> winners;

    public PrizeDrawDto() {
    }

    public PrizeDrawDto(String id,
                        String name,
                        String description,
                        LocalDateTime creationDate,
                        LocalDateTime endDate,
                        UserDto creator,
                        List<String> awards,
                        Set<String> participantsId,
                        List<String> auditLogsId,
                        List<ParticipantDto> winners) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.endDate = endDate;
        this.awards = awards;
        this.participantsId = participantsId;
        this.auditLogsId = auditLogsId;
        this.winners = winners;
    }

    public PrizeDrawDto(PrizeDraw entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.creationDate = entity.getCreationDate();
        this.endDate = entity.getEndDate();
        if (entity.getCreator() != null) {
            this.creator = new UserDto(entity.getCreator());
        }
        this.awards = entity.getAwards();
        this.participantsId = entity.getParticipantsId();
        this.auditLogsId = entity.getAuditLogsId();
        this.winners = entity.getWinners().stream().map(ParticipantDto::new).toList();
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

    public UserDto getCreator() {
        return creator;
    }

    public void setCreator(UserDto creator) {
        this.creator = creator;
    }

    public List<String> getAwards() {
        return awards;
    }

    public void setAwards(List<String> awards) {
        this.awards = awards;
    }

    public Set<String> getParticipantsId() {
        return participantsId;
    }

    public void setParticipantsId(Set<String> participantsId) {
        this.participantsId = participantsId;
    }

    public List<String> getAuditLogsId() {
        return auditLogsId;
    }

    public void setAuditLogsId(List<String> auditLogsId) {
        this.auditLogsId = auditLogsId;
    }

    public List<ParticipantDto> getWinners() {
        return winners;
    }

    public void setWinners(List<ParticipantDto> winners) {
        this.winners = winners;
    }
}
