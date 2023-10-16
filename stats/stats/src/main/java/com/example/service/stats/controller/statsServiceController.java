package com.example.service.stats.controller;

import com.example.service.stats.bean.Game;
import com.example.service.stats.delegate.GameServiceDelegate;
import com.example.service.stats.delegate.TeamServiceDelegate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/stats")
@Api(value = "Stats Service API", tags = "operations related to getting statisctics for team or player")
public class statsServiceController {

    @LoadBalanced
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TeamServiceDelegate teamServiceDelegate;

    @Autowired
    GameServiceDelegate gameServiceDelegate;

    @ApiOperation(value = "Get Statistics for a team by Id", response = String.class)
    @GetMapping("/team-stats/{teamId}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success !"),
            @ApiResponse(code = 401, message = "Not Authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!"),
            @ApiResponse(code = 404, message = "Not Found!")
    })
    public String getTeamStats(@PathVariable Long teamId) {
        // Check for team existence by calling TeamServiceDelegate
        String response = teamServiceDelegate.callTeamServiceAndCheckTeamExists(teamId);
        // check if the response corresponds to the fallBack one
        if(Objects.equals(response, "Circuit Breaker, No response from Team Service at the moment !")){
            return response;
        }
        // if the team does not exist the TeamService returns a default team with id 0 , check for that
        if(response.contains("<id>0</id><name>Not Found</name><country>N/A</country><numberOfPlayers>0</numberOfPlayers>")){
            return "The team you're requesting statistics for does not exist !";
        }
        // call GameServiceDelegate to get all games where teamId appears in one of the ids of the teams in the game
        String allGamesData = gameServiceDelegate.callGameServiceAndGetAllGamesWhereTeamPlayed(teamId);
        // check if the response corresponds to the one when Game service is down
        if(Objects.equals(allGamesData, "Circuit Breaker, No response from Game Service at the moment !")){
            return allGamesData;
        }
        // Implement logic to retrieve and return team statistics by teamId.
        return allGamesData;

    }

    @GetMapping("/player-stats/{playerId}")
    @ApiOperation(value = "Get player statistics", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public String getPlayerStats(@PathVariable Long playerId) {
        // Implement logic to retrieve and return player statistics by playerId.
        // You can return a JSON or any format suitable for your application.
        return "Player stats for player with ID " + playerId;
    }

}
