package com.example.matchservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@ApiModel(description = "Football match details")
public class FootballMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Match ID")
    private Long id;

    @ApiModelProperty(value = "Date of the match")
    private Date matchDate;

    @ApiModelProperty(value = "Name of the referee")
    private String refereeName;

    @ApiModelProperty(value = "Stadium where the match took place")
    private String stadium;

    @ApiModelProperty(value = "ID of the winning team")
    private Long winnerId;

    @ApiModelProperty(value = "ID of the losing team")
    private Long perdantId;

    @ApiModelProperty(value = "Indicates if the match ended in a draw")
    private boolean isMatchNull;

    @ApiModelProperty(value = "ID of the home team")
    private Long homeTeam;

    @ApiModelProperty(value = "ID of the away team")
    private Long awayTeam;

    @ApiModelProperty(value = "Score of the home team")
    private Long homeTeamScore;

    @ApiModelProperty(value = "Score of the away team")
    private Long awayTeamScore;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    @JsonManagedReference
    @ApiModelProperty(value = "List of team statistics for the match")
    private List<MatchTeamStat> matchTeamStats = new ArrayList<>(); // Initialisation avec une ArrayList vide

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    @JsonManagedReference
    @ApiModelProperty(value = "List of goal scorers in the match")
    private List<GoalScorer> goalScorers = new ArrayList<>(); // Initialisation avec une ArrayList vide

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    @JsonManagedReference
    @ApiModelProperty(value = "List of assists in the match")
    private List<Assist> assists = new ArrayList<>(); // Initialisation avec une ArrayList vide

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    @JsonManagedReference
    @ApiModelProperty(value = "List of appearance players in the match")
    private List<AppearancePlayer> appearancePlayers = new ArrayList<>(); // Initialisation avec une ArrayList vide



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public String getRefereeName() {
        return refereeName;
    }

    public void setRefereeName(String refereeName) {
        this.refereeName = refereeName;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }

    public Long getPerdantId() {
        return perdantId;
    }

    public void setPerdantId(Long perdantId) {
        this.perdantId = perdantId;
    }

    public boolean isMatchNull() {
        return isMatchNull;
    }

    public void setMatchNull(boolean matchNull) {
        isMatchNull = matchNull;
    }

    public Long getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Long homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Long getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Long awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Long getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(Long homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public Long getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(Long awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public List<MatchTeamStat> getMatchTeamStats() {
        return matchTeamStats;
    }

    public void setMatchTeamStats(List<MatchTeamStat> matchTeamStats) {
        this.matchTeamStats = matchTeamStats;
    }

    public List<GoalScorer> getGoalScorers() {
        return goalScorers;
    }

    public void setGoalScorers(List<GoalScorer> goalScorers) {
        this.goalScorers = goalScorers;
    }

    public List<Assist> getAssists() {
        return assists;
    }

    public void setAssists(List<Assist> assists) {
        this.assists = assists;
    }

    public List<Map<String, Object>> getAppearancePlayers() {
        // Regrouper les joueurs par équipe et stocker les identifiants des joueurs dans une liste
        Map<Long, List<Long>> playersGroupedByTeamId = appearancePlayers.stream()
                .collect(Collectors.groupingBy(
                        AppearancePlayer::getTeamId,
                        LinkedHashMap::new,
                        Collectors.mapping(AppearancePlayer::getPlayerId, Collectors.toList())
                ));

        return playersGroupedByTeamId.entrySet().stream()
                .map(entry -> {
                    // Créer une carte pour stocker les informations
                    Map<String, Object> teamPlayerMap = new LinkedHashMap<>();
                    // Ajouter l'identifiant de l'équipe comme clé "teamId"
                    teamPlayerMap.put("teamId", entry.getKey());
                    // Ajouter la liste des identifiants de joueurs comme clé "players"
                    teamPlayerMap.put("players", entry.getValue());
                    return teamPlayerMap;
                })
                .collect(Collectors.toList());
    }


    public void setAppearancePlayers(List<AppearancePlayer> appearancePlayers) {
        this.appearancePlayers = appearancePlayers;
    }
}
