package com.example.matchservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel(description = "Player assists in a football match")
@Entity
public class Assist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Assist ID")
    private Long id;

    @ApiModelProperty(value = "Number of assists by the player")
    private int assists;

    @ManyToOne
    @JoinColumn(name = "match_id")
    @JsonBackReference
    private FootballMatch match;

    @ApiModelProperty(value = "Player ID")
    private Long player;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public FootballMatch getMatch() {
        return match;
    }

    public void setMatch(FootballMatch match) {
        this.match = match;
    }

    public Long getPlayer() {
        return player;
    }

    public void setPlayer(Long player) {
        this.player = player;
    }
}
