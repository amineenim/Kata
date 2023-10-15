package com.example.games.controller;

import com.example.games.bean.Game;
import com.example.games.delegate.TeamServiceDelegate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@RestController
@RequestMapping("/matches")
@Api(value = "Games Service API", tags = "operations related to Game management")
public class GameServiceController {

    @Autowired
    TeamServiceDelegate teamServiceDelegate;

    private Map<Integer, Game> gameMap = new HashMap<>();
    private int currentId = 1;

    // Initialize some games
    {
        Game game1 = new Game(currentId, 1L, 2L); // ID, Team 1 ID, Team 2 ID
        gameMap.put(currentId, game1);
        currentId++;

        Game game2 = new Game(currentId, 2L, 3L); // ID, Team 2 ID, Team 3 ID
        gameMap.put(currentId, game2);
        currentId++;
        // Add more games as needed
    }

    //method that checks if the score is having a valid format
    private boolean isValidScoreFormat(Map<Long, Integer> score, Long[] teamIds) {
        if (score == null) {
            return true; // No score is a valid format
        }

        Set<Long> teamIdSet = new HashSet<>(Arrays.asList(teamIds));

        for (Map.Entry<Long, Integer> entry : score.entrySet()) {
            // checking if the value of the team score is null
            // checking if the value is not negative
            // checking if the key corresponding to the value is actually existing in the teamsIds
            // for example if you update a game to be as follows :
            // teamsIds = [1, 4] the score must be necessarly of the format : {"1" : value, "4" : value}
            // the order of the keys is not important
            if (entry.getValue() == null || entry.getValue() < 0 || !teamIdSet.contains(entry.getKey())) {
                return false;
            }
        }
        return true;
    }

    @ApiOperation(value = "Get all games", response = Map.class)
    @GetMapping
    public Map<Integer, Game> getAllGames(){
        return gameMap;
    }


    @ApiOperation(value = "Get a gane by id", response = Game.class)
    @ApiParam(value = "ID of the game", required = true)
    @GetMapping("/{id}")
    public Game getGame(@PathVariable Integer id) {
        Game game = gameMap.get(id);
        if (game != null) {
            return game; // return the game details as a string.
        } else {
            // when the game is not found, return this game by default
            return new Game(0, 0L, 0L);
        }
    }

    @ApiOperation(value = "Create new game", response = String.class)
    @PostMapping
    public String addGame(@RequestBody Game game) {
        // check for all required params for creating a new game
        if(game.getId() == null || game.getTeamIds() == null){
            return "Id for the game, and ids of the teams in the game are required !" + game.getId();
        }
        // define a boolean that represents if the teams ids are correct
        boolean teamsIds = true;
        // check if the ids given correspond actually to existing teams
        for(Long teamId : game.getTeamIds()){
            // call TeamServiceDelegate a check for existence of each team
            String response = teamServiceDelegate.callTeamServiceAndCheckTeamExists(teamId);
            // check if response corresponds to the fallBack one when Team Service is down
            if(Objects.equals(response, "Circuit Breaker, No response from Team Service at the moment !")){
                return response;
            }
            // when a team does not exist we return a default team with id 0
            // check if the returned response is containing id with value of 0
            // the default team returned when a team is not found is having an id of 0
            if(response.contains("<id>0</id>")){
                teamsIds = false;
                break;
            }
        }
        if(teamsIds){
            // if the teams ids are correct. check if the ids are not the same , a team can not
            // play against himself
            if(game.getTeamIds()[0].equals(game.getTeamIds()[1])){
                return "Invalid Game, you should choose different teams";
            }
            game.setId(currentId);
            gameMap.put(currentId, game);
            currentId++;
            return "Game created !" ;
        }else{
            return "Teams Ids are not compatible, check the ids for the teams !";
        }
    }


    @ApiOperation(value = "Update a game", response = String.class)
    @ApiParam(value = "ID of the team", required = true)
    @PutMapping("/{id}")
    public String updateGame(@PathVariable Integer id, @RequestBody Game updatedGame) {
        Game existingGame = gameMap.get(id);
        if(existingGame == null){
            return "Game Not found!";
        }
        Long firstTeamId = updatedGame.getTeamIds()[0];
        Long secondTeamId = updatedGame.getTeamIds()[1];

        if (firstTeamId.equals(secondTeamId)) {
            return "The first and second team IDs cannot be the same.";
        }
        // request Teams service for details for each team
        boolean teamsExist = true;
        for(Long teamId : updatedGame.getTeamIds()){
            // talk To TeamService and check existance of both teamsIds
            String response = teamServiceDelegate.callTeamServiceAndCheckTeamExists(teamId);
            if(Objects.equals(response, "Circuit Breaker, No response from Team Service at the moment !")){
                return response;
            }
            // if a team does not exist, a default team  with id 0 is returned
            if(response.contains("<id>0</id>")){
                teamsExist = false;
                break;
            }
        }
        if(teamsExist){
            // Update the game's attributes
            existingGame.setTeamIds(updatedGame.getTeamIds());
            // Update the score only if the request contains scores
            if (updatedGame.getScore() != null) {
                // check if the score conforms to the updated format
                if(isValidScoreFormat(updatedGame.getScore(), updatedGame.getTeamIds())){
                    existingGame.setScore(updatedGame.getScore());
                    // Update the winner based on the new score
                    existingGame.updateWinner();
                }else{
                    return "the score format is invalid ! check the keys in score are corresponding to teams ids, the score for both teams must be integer and greater or equals 0 !!";
                }
            }

            gameMap.put(id, existingGame);
            return "Game updated with ID: " + existingGame.getId();
        }else{
            return "Check your teams'ids , they are not correct !";
        }
    }

    @ApiOperation(value = "Delete a game", response = String.class)
    @ApiParam(value = "ID of the team", required = true)
    @DeleteMapping("/{id}")
    public String deleteGame(@PathVariable Integer id) {
        Game game = gameMap.remove(id);
        if (game != null) {
            return "Game with ID " + id + " deleted.";
        } else {
            return "Game not found.";
        }
    }

}
