package com.example.matchservice.controller;

import com.example.matchservice.model.GoalScorer;
import com.example.matchservice.service.GoalScorerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goalscorers")
@Api(value = "Goal Scorer Controller", description = "API endpoints for goal scorers")
public class GoalScorerController {
    private final GoalScorerService goalScorerService;

    @Autowired
    public GoalScorerController(GoalScorerService goalScorerService) {
        this.goalScorerService = goalScorerService;
    }

    @GetMapping
    @ApiOperation(value = "Get all goal scorers", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public List<GoalScorer> getAllGoalScorers() {
        return goalScorerService.getAllGoalScorers();
    }

    @GetMapping("/{playerId}")
    @ApiOperation(value = "Get the amount of goals for a player", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public Map<String, Object> getAmountOfGoalsForPlayer(
            @ApiParam(value = "Player ID", required = true)
            @PathVariable Long playerId) {
        return goalScorerService.getGoalsAmountForPlayer(playerId);
    }

    @PostMapping
    @ApiOperation(value = "Create a new goal scorer", response = GoalScorer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!")
    })
    public GoalScorer createGoalScorer(
            @ApiParam(value = "Goal Scorer data", required = true)
            @RequestBody GoalScorer goalScorer) {
        return goalScorerService.createGoalScorer(goalScorer);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a goal scorer by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public void deleteGoalScorer(
            @ApiParam(value = "Goal Scorer ID", required = true)
            @PathVariable Long id) {
        goalScorerService.deleteGoalScorer(id);
    }
}
