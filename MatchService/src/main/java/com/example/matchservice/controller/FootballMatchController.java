package com.example.matchservice.controller;

import com.example.matchservice.model.FootballMatch;
import com.example.matchservice.model.PlayerStatisticsDTO;
import com.example.matchservice.model.TeamStatisticsDTO;
import com.example.matchservice.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/football-matches")
@Api(value = "Football Match Controller", description = "API endpoints for football matches")
public class FootballMatchController {
    private final FootballMatchService footballMatchService;
    private final AppearancePlayerService appearancePlayerService;
    private final GoalScorerService goalScorerService;
    private final AssistService assistService;
    private final TeamPlayerService teamPlayerService;

    @Autowired
    public FootballMatchController(FootballMatchService footballMatchService, AppearancePlayerService appearancePlayerService, GoalScorerService goalScorerService, AssistService assistService, TeamPlayerService teamPlayerService) {
        this.footballMatchService = footballMatchService;
        this.appearancePlayerService = appearancePlayerService;
        this.goalScorerService = goalScorerService;
        this.assistService = assistService;
        this.teamPlayerService = teamPlayerService;
    }

    @Autowired
    Environment environment;

    @GetMapping
    @ApiOperation(value = "Get all football matches", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public List<FootballMatch> getAllFootballMatches() {
        return footballMatchService.getAllFootballMatches();
    }

    @GetMapping("/backend")
    @ApiOperation(value = "Test Load Balancing Simulation", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public String backend() {

        String serverPort = environment.getProperty("local.server.port");

        return "Short test with my server port that is  :: " + serverPort ;
    }

    @GetMapping("/stats/team/{teamId}")
    @ApiOperation(value = "Get football match statistics by team", response = TeamStatisticsDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public TeamStatisticsDTO getFootBallMatchStatByTeam(
            @ApiParam(value = "Team ID", required = true)
            @PathVariable Long teamId) {
        return footballMatchService.getAllFootballMatchStatByTeam(teamId);
    }

    @GetMapping("/stats/player/{playerId}")
    @ApiOperation(value = "Get football match statistics by player", response = PlayerStatisticsDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public PlayerStatisticsDTO getFootBallMatchStatByPlayer(
            @ApiParam(value = "Player ID", required = true)
            @PathVariable Long playerId) {
        Integer appearances = 0;
        Integer goalsScored = 0;
        Integer assists = 0;
        Object player = teamPlayerService.getPlayerById(playerId);

        Map<String, Object> appearancesMap = appearancePlayerService.getMatchPlayedPlayersForPlayer(playerId);
        if (appearancesMap != null) {
            appearances = (Integer) appearancesMap.get("appearances");
        }

        Map<String, Object> goalsScoredMap = goalScorerService.getGoalsAmountForPlayer(playerId);
        if (goalsScoredMap != null) {
            goalsScored = (Integer) goalsScoredMap.get("goals");
        }

        Map<String, Object> assistsMap = assistService.getAssistsAmountForPlayer(playerId);
        if (assistsMap != null) {
            assists = (Integer) assistsMap.get("assists");
        }

        return new PlayerStatisticsDTO(player, appearances, goalsScored, assists);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get football match by ID", response = FootballMatch.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public Optional<FootballMatch> getFootballMatchById(
            @ApiParam(value = "Football Match ID", required = true)
            @PathVariable Long id) {
        return footballMatchService.getFootballMatchById(id);
    }

    @PostMapping
    @ApiOperation(value = "Create a new football match", response = FootballMatch.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!")
    })
    public FootballMatch createFootballMatch(
            @ApiParam(value = "Football match data", required = true)
            @RequestBody FootballMatch footballMatch) {
        return footballMatchService.createFootballMatch(footballMatch);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing football match", response = FootballMatch.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public FootballMatch updateFootballMatch(
            @ApiParam(value = "Football Match ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Updated football match data", required = true)
            @RequestBody FootballMatch updatedFootballMatch) {
        return footballMatchService.updateFootballMatch(id, updatedFootballMatch);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a football match by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public void deleteFootballMatch(
            @ApiParam(value = "Football Match ID", required = true)
            @PathVariable Long id) {
        footballMatchService.deleteFootballMatch(id);
    }
}