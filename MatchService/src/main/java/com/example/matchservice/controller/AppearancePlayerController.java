package com.example.matchservice.controller;

import com.example.matchservice.model.AppearancePlayer;
import com.example.matchservice.service.AppearancePlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/players-appearances")
@Api(value = "Appearance Player Controller", description = "API endpoints for appearance players")
public class AppearancePlayerController {

    private final AppearancePlayerService appearancePlayerService;

    @Autowired
    public AppearancePlayerController(AppearancePlayerService appearancePlayerService) {
        this.appearancePlayerService = appearancePlayerService;
    }

    @GetMapping("/{playerId}")
    @ApiOperation(value = "Get appearances for a player", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public Map<String, Object> getMatchPlayedPlayersForPlayer(
            @ApiParam(value = "Player ID", required = true)
            @PathVariable Long playerId) {
        return appearancePlayerService.getMatchPlayedPlayersForPlayer(playerId);
    }

    @PostMapping
    @ApiOperation(value = "Add an appearance for a player", response = AppearancePlayer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!")
    })
    public AppearancePlayer addMatchPlayedPlayer(
            @ApiParam(value = "Appearance player data", required = true)
            @RequestBody AppearancePlayer appearancePlayer) {
        return appearancePlayerService.addMatchPlayedPlayer(appearancePlayer);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an appearence for a player by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public void deleteMatchPlayedPlayer(
            @ApiParam(value = "appearance ID", required = true)
            @PathVariable Long id) {
        appearancePlayerService.deleteMatchPlayedPlayer(id);
    }
}
