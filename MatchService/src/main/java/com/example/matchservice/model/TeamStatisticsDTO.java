package com.example.matchservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Team statistics data transfer object")
public class TeamStatisticsDTO {

    @ApiModelProperty(value = "Team information")
    private Object team;

    @ApiModelProperty(value = "Number of matches played by the team")
    private Long matchesPlayed;

    @ApiModelProperty(value = "Total number of goals scored by the team")
    private Long goalsScored;

    @ApiModelProperty(value = "Total number of goals conceded by the team")
    private Long goalsConceded;

    @ApiModelProperty(value = "Number of wins by the team")
    private Long wins;

    @ApiModelProperty(value = "Number of losses by the team")
    private Long losses;

    @ApiModelProperty(value = "Number of draws by the team")
    private Long draws;

    @ApiModelProperty(value = "Goal difference for the team")
    private Long goalDifference;

    public TeamStatisticsDTO() {
    }

    public TeamStatisticsDTO(Object team, Long matchesPlayed, Long goalsScored, Long goalsConceded, Long wins, Long losses, Long draws, Long goalDifference) {
        this.team = team;
        this.matchesPlayed = matchesPlayed;
        this.goalsScored = goalsScored;
        this.goalsConceded = goalsConceded;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.goalDifference = goalDifference;
    }

    public Object getTeam() {
        return team;
    }

    public Long getMatchesPlayed() {
        return matchesPlayed;
    }

    public Long getGoalsScored() {
        return goalsScored;
    }

    public Long getGoalsConceded() {
        return goalsConceded;
    }

    public Long getWins() {
        return wins;
    }

    public Long getLosses() {
        return losses;
    }

    public Long getDraws() {
        return draws;
    }

    public Long getGoalDifference() {
        return goalDifference;
    }
}
