package com.example.service.stats.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

public class Game {
    @ApiModelProperty(notes = "id of the game", name = "id", required = true, value = "test id")
    private Integer id;
    @ApiModelProperty(notes = "ids of the teams in the game", name = "teamsIds", required = true, value = "test teamsIds")
    private Long[] teamIds;
    @ApiModelProperty(notes = "score of both teams in the game", name = "score", value = "test score")
    private Map<Long, Integer> score;
    @ApiModelProperty(notes = "id of the winner of the game", name = "winnerId", value = "test winnerId")
    private Long winnerId;


    @JsonCreator
    public Game(@JsonProperty("id") Integer id,@JsonProperty("firstTeamId") Long firstTeamId,@JsonProperty("secondTeamId") Long secondTeamId) {
        this.id = id;
        this.teamIds = new Long[]{firstTeamId, secondTeamId};
        this.score = new HashMap<>();
        // Initialize scores for each team to 0
        for (Long teamId : teamIds) {
            score.put(teamId, 0);
        }
        this.winnerId = 0L;
    }

    // Getters and setters for the attributes

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long[] getTeamIds() {
        return teamIds;
    }

    public void setTeamIds(Long[] teamIds) {
        this.teamIds = teamIds;
    }

    public Map<Long, Integer> getScore() {
        return score;
    }

    public void setScore(Map<Long, Integer> score) {
        this.score = score;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }

    // Method that update the winnerId based on the score
    public void updateWinner() {
        Long firstTeamId = teamIds[0];
        Long secondTeamId = teamIds[1];

        Integer firstTeamScore = score.get(firstTeamId);
        Integer secondTeamScore = score.get(secondTeamId);

        if (firstTeamScore > secondTeamScore) {
            winnerId = firstTeamId;
        } else if (secondTeamScore > firstTeamScore) {
            winnerId = secondTeamId;
        } else {
            winnerId = 0L; // when there is a tie , both teams having the same number of goals
        }
    }

}