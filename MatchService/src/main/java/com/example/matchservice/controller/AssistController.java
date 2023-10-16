package com.example.matchservice.controller;

import com.example.matchservice.model.Assist;
import com.example.matchservice.service.AssistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/assists")
@Api(value = "Assist Controller", description = "API endpoints for assists")
public class AssistController {
    private final AssistService assistService;

    @Autowired
    public AssistController(AssistService assistService) {
        this.assistService = assistService;
    }

    @GetMapping
    @ApiOperation(value = "Get all assists", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public List<Assist> getAllAssists() {
        return assistService.getAllAssists();
    }

    @GetMapping("/{playerId}")
    @ApiOperation(value = "Get the amount of assists for a player", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public Map<String, Object> getAmountOfAssistsForPlayer(
            @ApiParam(value = "Player ID", required = true)
            @PathVariable Long playerId) {
        return assistService.getAssistsAmountForPlayer(playerId);
    }

    @PostMapping
    @ApiOperation(value = "Create a new assist", response = Assist.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!")
    })
    public Assist createAssist(
            @ApiParam(value = "Assist data", required = true)
            @RequestBody Assist assist) {
        return assistService.createAssist(assist);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an assist by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public void deleteAssist(
            @ApiParam(value = "Assist ID", required = true)
            @PathVariable Long id) {
        assistService.deleteAssist(id);
    }
}
