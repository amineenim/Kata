package com.example.matchservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Player statistics data transfer object")
public class PlayerStatisticsDTO {

    @ApiModelProperty(value = "Player information")
    private Object player;

    @ApiModelProperty(value = "Number of appearances by the player")
    private Integer appearances;

    @ApiModelProperty(value = "Number of goals scored by the player")
    private Integer goalsScored;

    @ApiModelProperty(value = "Number of assists made by the player")
    private Integer assists;

    public PlayerStatisticsDTO() {
    }

    public PlayerStatisticsDTO(Object player, Integer appearances, Integer goalsScored, Integer assists) {
        this.player = player;
        this.appearances = appearances;
        this.goalsScored = goalsScored;
        this.assists = assists;
    }

    public Object getPlayer() {
        return player;
    }

    public Integer getAppearances() {
        return appearances;
    }

    public Integer getGoalsScored() {
        return goalsScored;
    }

    public Integer getAssists() {
        return assists;
    }
}
