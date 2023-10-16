package com.example.matchservice.controller;

import com.example.matchservice.exception.MatchTeamStatNotFoundException;
import com.example.matchservice.model.MatchTeamStat;
import com.example.matchservice.service.MatchTeamStatService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/match-team-stats")
@Api(value = "Match Team Stat Controller", description = "API endpoints for match team statistics")
public class MatchTeamStatController {
    private final MatchTeamStatService matchTeamStatService;

    @Autowired
    public MatchTeamStatController(MatchTeamStatService matchTeamStatService) {
        this.matchTeamStatService = matchTeamStatService;
    }

    @GetMapping
    @ApiOperation(value = "Get all match team statistics", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public List<MatchTeamStat> getAllMatchTeamStats() {
        return matchTeamStatService.getAllMatchTeamStats();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get match team statistics by ID", response = MatchTeamStat.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public MatchTeamStat getMatchTeamStatById(
            @ApiParam(value = "Match Team Stat ID", required = true)
            @PathVariable Long id) {
        Optional<MatchTeamStat> matchTeamStat = matchTeamStatService.getMatchTeamStatById(id);
        if (matchTeamStat.isPresent()) {
            return matchTeamStat.get();
        } else {
            // Gérer le cas où l'entité n'est pas trouvée
            throw new MatchTeamStatNotFoundException(id);
        }
    }

    @PostMapping
    @ApiOperation(value = "Create a new match team statistic", response = MatchTeamStat.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!")
    })
    public MatchTeamStat createMatchTeamStat(
            @ApiParam(value = "Match Team Stat data", required = true)
            @RequestBody MatchTeamStat matchTeamStat) {
        return matchTeamStatService.createMatchTeamStat(matchTeamStat);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing match team statistic", response = MatchTeamStat.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public MatchTeamStat updateMatchTeamStat(
            @ApiParam(value = "Match Team Stat ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Updated match team statistic data", required = true)
            @RequestBody MatchTeamStat updatedMatchTeamStat) {
        return matchTeamStatService.updateMatchTeamStat(id, updatedMatchTeamStat);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a match team statistic by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public void deleteMatchTeamStat(
            @ApiParam(value = "Match Team Stat ID", required = true)
            @PathVariable Long id) {
        matchTeamStatService.deleteMatchTeamStat(id);
    }
}
