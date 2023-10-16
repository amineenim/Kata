package com.example.matchservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel(description = "Player appearance in a football match")
@Entity
public class AppearancePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Appearance ID")
    private Long id;

    @ApiModelProperty(value = "Player ID")
    private Long playerId;

    @ApiModelProperty(value = "Team ID to which the player belongs")
    private Long teamId;

    @ApiModelProperty(value = "Indicates if the player was a starter")
    private boolean isStarter;

    @ManyToOne
    @JoinColumn(name = "match_id")
    @JsonBackReference
    private FootballMatch match;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public boolean isStarter() {
        return isStarter;
    }

    public void setStarter(boolean starter) {
        isStarter = starter;
    }

    public FootballMatch getMatch() {
        return match;
    }

    public void setMatch(FootballMatch match) {
        this.match = match;
    }
}
