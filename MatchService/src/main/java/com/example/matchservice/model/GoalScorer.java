package com.example.matchservice.model;

import com.example.matchservice.model.FootballMatch;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@ApiModel(description = "Goal scorer details")
@Entity
public class GoalScorer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Goal scorer ID")
    private Long id;

    @ApiModelProperty(value = "Number of goals scored by the player")
    private int goals;

    @ManyToOne
    @JoinColumn(name = "match_id")
    @JsonBackReference
    @ApiModelProperty(value = "Match associated with the goal scorer")
    private FootballMatch match;

    @ApiModelProperty(value = "ID of the player who scored the goals")
    private Long player;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
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

